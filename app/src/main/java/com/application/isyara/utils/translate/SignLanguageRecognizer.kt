package com.application.isyara.utils.translate

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.os.SystemClock
import androidx.camera.core.ImageProxy
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import timber.log.Timber
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class SignLanguageRecognizer(
    private val context: Context,
    private var listener: LandmarkerListener? = null,
    private val config: Config = Config()
) : AutoCloseable {
    private var handLandmarker: HandLandmarker? = null
    private var interpreter: Interpreter? = null
    private var classLabels: Array<String> = Array(26) { ('A' + it).toString() }

    init {
        initializeHandLandmarker()
        initializeTFLiteInterpreter()
    }

    private fun initializeHandLandmarker() {
        try {
            handLandmarker?.close()

            val baseOptions = BaseOptions.builder()
                .setModelAssetPath(config.modelAssetPath)
                .build()

            val options = HandLandmarker.HandLandmarkerOptions.builder()
                .setBaseOptions(baseOptions)
                .setMinHandDetectionConfidence(config.minHandDetectionConfidence)
                .setMinTrackingConfidence(config.minHandTrackingConfidence)
                .setMinHandPresenceConfidence(config.minHandPresenceConfidence)
                .setNumHands(config.maxNumHands)
                .setRunningMode(config.runningMode)
                .setResultListener(::handleDetectionResult)
                .setErrorListener(::handleDetectionError)
                .build()
            handLandmarker = HandLandmarker.createFromOptions(context, options)
            config.isDetecting = true
            Timber.d("HandLandmarker berhasil diinisialisasi")
        } catch (e: IOException) {
            Timber.e("Gagal menginisialisasi HandLandmarker: " + e.message)
            listener?.onError("Gagal menginisialisasi HandLandmarker: ${e.message}", OTHER_ERROR)
        }
    }

    /**
     * Menginisialisasi TensorFlow Lite Interpreter
     */
    private fun initializeTFLiteInterpreter() {
        try {
            interpreter?.close()

            val modelBuffer = loadModelFile(MODEL_ISYARA)
            val options = Interpreter.Options().apply {
                numThreads = 4
                useNNAPI = true
            }
            interpreter = Interpreter(modelBuffer, options)
            Timber.d("TensorFlow Lite Interpreter berhasil diinisialisasi dengan delegasi " + config.currentDelegate)
        } catch (e: IOException) {
            Timber.e("Gagal menginisialisasi TensorFlow Lite Interpreter: " + e.message)
            listener?.onError(
                "Gagal menginisialisasi TensorFlow Lite Interpreter: ${e.message}",
                OTHER_ERROR
            )
        }
    }

    @Throws(IOException::class)
    private fun loadModelFile(modelPath: String): MappedByteBuffer {
        context.assets.openFd(modelPath).use { fileDescriptor ->
            FileInputStream(fileDescriptor.fileDescriptor).use { inputStream ->
                val fileChannel = inputStream.channel
                val startOffset = fileDescriptor.startOffset
                val declaredLength = fileDescriptor.declaredLength
                return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
            }
        }
    }

    private fun runInference(inputCoords: FloatArray): String {
        interpreter?.let { tflite ->
            try {
                val inputShape = tflite.getInputTensor(0).shape()
                val inputDataType = tflite.getInputTensor(0).dataType()
                val outputShape = tflite.getOutputTensor(0).shape()
                val outputDataType = tflite.getOutputTensor(0).dataType()

                Timber.d("TFLite Input Shape: " + inputShape.contentToString() + ", Type: " + inputDataType)
                Timber.d("TFLite Output Shape: " + outputShape.contentToString() + ", Type: " + outputDataType)

                if (inputShape.size != 2 || inputShape[0] != 1 || inputShape[1] != inputCoords.size) {
                    Timber.e("Bentuk input tidak sesuai: Diharapkan [1, " + inputCoords.size + "], tapi yang diterima adalah " + inputShape.contentToString())
                    return "Input Shape Error"
                }

                val inputBuffer = TensorBuffer.createFixedSize(inputShape, inputDataType)
                inputBuffer.loadArray(inputCoords)

                val outputBuffer = TensorBuffer.createFixedSize(outputShape, outputDataType)

                tflite.run(inputBuffer.buffer, outputBuffer.buffer)
                Timber.d("Inferensi berhasil dijalankan")

                return when (outputDataType) {
                    DataType.FLOAT32 -> {
                        val outputArray = outputBuffer.floatArray
                        Timber.d("TFLite Output Array: " + outputArray.contentToString())

                        val predictedIndex =
                            outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
                        if (predictedIndex in classLabels.indices) {
                            classLabels[predictedIndex]
                        } else {
                            "Tidak Dikenali"
                        }
                    }

                    else -> {
                        Timber.e("Tipe data output tidak didukung: $outputDataType")
                        "Unsupported Output Type"
                    }
                }
            } catch (e: Exception) {
                Timber.e("Error selama inferensi: " + e.message)
                return "Inference Error"
            }
        } ?: run {
            Timber.e("Interpreter belum diinisialisasi")
            return "Interpreter Error"
        }
    }

    /**
     * Memproses frame dari live stream
     */

    fun detectLiveStream(imageProxy: ImageProxy, isFrontCamera: Boolean) {
        try {
            if (!config.isDetecting) {
                imageProxy.close()
                Timber.d("SignLanguageRecognizer: isDetecting=false, frame ditutup")
                return
            }

            val frameTime = SystemClock.uptimeMillis()
            val bitmap = imageProxy.createToBitmap()
            imageProxy.close()
            val rotatedBitmap =
                rotateAndFlipBitmap(bitmap, imageProxy.imageInfo.rotationDegrees, isFrontCamera)
            val mpImage = BitmapImageBuilder(rotatedBitmap).build()
            Timber.d("SignLanguageRecognizer: Memproses frame dengan waktu: $frameTime")
            detectAsync(mpImage, frameTime)
        } catch (e: Exception) {
            Timber.e(e, "SignLanguageRecognizer: Error during detectLiveStream")
            imageProxy.close()
        }
    }

    private fun ImageProxy.createToBitmap(): Bitmap {
        val plane = planes[0]
        val buffer = plane.buffer
        val bytes = ByteArray(buffer.remaining())
        buffer.get(bytes)
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            copyPixelsFromBuffer(java.nio.ByteBuffer.wrap(bytes))
        }
    }

    private fun detectAsync(mpImage: MPImage, frameTime: Long) {
        handLandmarker?.detectAsync(mpImage, frameTime)
    }

    private fun handleDetectionResult(result: HandLandmarkerResult, input: MPImage) {
        val inferenceTime = SystemClock.uptimeMillis() - result.timestampMs()
        val landmarksList = result.landmarks()

        Timber.d("handleDetectionResult: Detected ${landmarksList.size} landmarks")

        val predictions = landmarksList.mapNotNull { landmarks ->
            val inputArray = preprocessLandmarks(landmarks)
            val inferenceResult = runInference(inputArray)
            Timber.d("handleDetectionResult: Inference result: $inferenceResult")
            if (inferenceResult.length == 1 && inferenceResult[0].isLetter()) {
                inferenceResult
            } else {
                null
            }
        }
        predictions.forEach { prediction ->
            Timber.d("handleDetectionResult: Prediction sent: $prediction")
            listener?.onResults(
                ResultBundle(
                    results = listOf(result),
                    inferenceTime = inferenceTime,
                    inputImageHeight = input.height,
                    inputImageWidth = input.width,
                    prediction = prediction
                )
            )
        }
    }

    private fun handleDetectionError(error: RuntimeException) {
        Timber.e("handleDetectionError: ${error.message}")
        val errorCode = if (config.currentDelegate == DELEGATE_CPU) OTHER_ERROR else GPU_ERROR
        listener?.onError(error.message ?: "Terjadi error tidak dikenal", errorCode)
    }

    /**
     * Preprocessing landmarks untuk inferensi
     */
    private fun preprocessLandmarks(landmarks: List<NormalizedLandmark>): FloatArray {
        val input = FloatArray(21 * 2)
        for (i in 0 until 21) {
            val landmark = landmarks.getOrNull(i) ?: continue
            input[i * 2] = landmark.x()
            input[i * 2 + 1] = landmark.y()
            Timber.d("Preprocess: Landmark " + i + " -> x=" + input[i * 2] + ", y=" + input[i * 2 + 1])
        }
        return input
    }

    private fun rotateAndFlipBitmap(
        bitmap: Bitmap,
        rotationDegrees: Int,
        isFrontCamera: Boolean
    ): Bitmap {
        val matrix = Matrix().apply {
            postRotate(rotationDegrees.toFloat())
            if (isFrontCamera) {
                postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
            }
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    private fun stopLiveStream() {
        handLandmarker?.close()
        handLandmarker = null
        config.isDetecting = false
        Timber.d("SignLanguageRecognizer: Live stream stopped")
    }

    override fun close() {
        handLandmarker?.close()
        interpreter?.close()
        handLandmarker = null
        interpreter = null
        stopLiveStream()
        Timber.d("SignLanguageRecognizer: Closed and resources cleaned up")
    }

    fun stopTranslation() {
        handLandmarker?.close()
        handLandmarker = null
        config.isDetecting = false
        Timber.d("SignLanguageRecognizer: Live stream stopped")
    }

    companion object {
        private const val DEFAULT_MODEL_ASSET_PATH = "hand_landmarker.task"
        private const val MODEL_ISYARA = "isyara.tflite"
        const val DELEGATE_CPU = 0
        const val DEFAULT_HAND_DETECTION_CONFIDENCE = 0.8F
        const val DEFAULT_HAND_TRACKING_CONFIDENCE = 0.8F
        const val DEFAULT_HAND_PRESENCE_CONFIDENCE = 0.8F
        const val DEFAULT_NUM_HANDS = 1
        const val OTHER_ERROR = 0
        const val GPU_ERROR = 1
    }

    data class Config(
        var minHandDetectionConfidence: Float = DEFAULT_HAND_DETECTION_CONFIDENCE,
        var minHandTrackingConfidence: Float = DEFAULT_HAND_TRACKING_CONFIDENCE,
        var minHandPresenceConfidence: Float = DEFAULT_HAND_PRESENCE_CONFIDENCE,
        var isDetecting: Boolean = false,
        var maxNumHands: Int = DEFAULT_NUM_HANDS,
        var currentDelegate: Int = DELEGATE_CPU,
        var runningMode: RunningMode = RunningMode.LIVE_STREAM,
        var modelAssetPath: String = DEFAULT_MODEL_ASSET_PATH,
    )
}
