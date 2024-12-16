package com.application.isyara.utils.translate

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.YuvImage
import android.os.SystemClock
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageProxy
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.framework.image.MPImage
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarker
import com.google.mediapipe.tasks.vision.handlandmarker.HandLandmarkerResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.gpu.GpuDelegate
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
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
    private val coroutineScope = CoroutineScope(Dispatchers.Default)
    private var isProcessing: Boolean = false
    private var frameStartTimeMillis: Long = 0L

    // Variabel untuk menyimpan status kamera saat ini
    @Volatile
    private var currentIsFrontCamera: Boolean = true

    init {
        initializeHandLandmarker()
        initializeTFLiteInterpreter()
    }

    /**
     * Menginisialisasi HandLandmarker dengan opsi yang diberikan.
     */
    private fun initializeHandLandmarker() {
        try {
            handLandmarker?.close()

            val modelFile = File(context.filesDir, config.modelAssetPath)
            when {
                !modelFile.exists() -> {
                    throw IOException("Model file not found at ${modelFile.absolutePath}")
                }
                else -> {
                    val baseOptions = BaseOptions.builder()
                        .setModelAssetPath(modelFile.absolutePath)
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
                }
            }
        } catch (e: IOException) {
            Timber.e("Gagal menginisialisasi HandLandmarker: ${e.message}")
            listener?.onError("Gagal menginisialisasi HandLandmarker: ${e.message}", OTHER_ERROR)
        }
    }

    /**
     * Menginisialisasi TensorFlow Lite Interpreter dengan opsi yang diberikan.
     */
    private fun initializeTFLiteInterpreter() {
        try {
            interpreter?.close()

            val modelFile = File(context.filesDir, config.tfliteModelAssetPath)
            if (!modelFile.exists()) {
                throw IOException("TensorFlow Lite model file not found at ${modelFile.absolutePath}")
            }

            val modelBuffer = loadModelFile(modelFile)
            val options = Interpreter.Options().apply {
                when (config.currentDelegate) {
                    DELEGATE_CPU -> {
                        numThreads = 4
                        useNNAPI = true
                        Timber.d("Menggunakan delegasi CPU")
                    }

                    else -> {
                        Timber.d("Menggunakan delegasi CPU default")
                    }
                }
            }
            interpreter = Interpreter(modelBuffer, options)
            Timber.d("TensorFlow Lite Interpreter berhasil diinisialisasi dengan delegasi ${config.currentDelegate}")
        } catch (e: IOException) {
            Timber.e("Gagal menginisialisasi TensorFlow Lite Interpreter: ${e.message}")
            listener?.onError(
                "Gagal menginisialisasi TensorFlow Lite Interpreter: ${e.message}",
                OTHER_ERROR
            )
        }
    }

    /**
     * Memuat file model TensorFlow Lite dari penyimpanan internal.
     */
    @Throws(IOException::class)
    private fun loadModelFile(modelFile: File): MappedByteBuffer {
        FileInputStream(modelFile).use { inputStream ->
            val fileChannel = inputStream.channel
            val startOffset = 0L
            val declaredLength = fileChannel.size()
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        }
    }

    /**
     * Menjalankan inferensi pada input koordinat dan mengembalikan hasil prediksi.
     */
    private fun runInference(inputCoords: FloatArray): String {
        interpreter?.let { tflite ->
            try {
                val inputShape = tflite.getInputTensor(0).shape()
                val inputDataType = tflite.getInputTensor(0).dataType()
                val outputShape = tflite.getOutputTensor(0).shape()
                val outputDataType = tflite.getOutputTensor(0).dataType()

                Timber.d("TFLite Input Shape: ${inputShape.contentToString()}, Type: $inputDataType")
                Timber.d("TFLite Output Shape: ${outputShape.contentToString()}, Type: $outputDataType")

                // Validasi bentuk input
                when {
                    inputShape.size != 2 || inputShape[0] != 1 || inputShape[1] != inputCoords.size -> {
                        Timber.e("Bentuk input tidak sesuai: Diharapkan [1, ${inputCoords.size}], tapi yang diterima adalah ${inputShape.contentToString()}")
                        return "Input Shape Error"
                    }
                    else -> {
                        val inputBuffer = TensorBuffer.createFixedSize(inputShape, inputDataType)
                        inputBuffer.loadArray(inputCoords)

                        val outputBuffer = TensorBuffer.createFixedSize(outputShape, outputDataType)

                        tflite.run(inputBuffer.buffer, outputBuffer.buffer)
                        Timber.d("Inferensi berhasil dijalankan")

                        return when (outputDataType) {
                            DataType.FLOAT32 -> {
                                val outputArray = outputBuffer.floatArray
                                Timber.d("TFLite Output Array: ${outputArray.contentToString()}")

                                val predictedIndex =
                                    outputArray.indices.maxByOrNull { outputArray[it] } ?: -1
                                val confidence = outputArray.getOrNull(predictedIndex) ?: 0f
                                val confidenceThreshold = 0.5f

                                if (confidence >= confidenceThreshold && predictedIndex in classLabels.indices) {
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
                    }
                }
            } catch (e: Exception) {
                Timber.e("Error selama inferensi: ${e.message}")
                return "Inference Error"
            }
        } ?: run {
            Timber.e("Interpreter belum diinisialisasi")
            return "Interpreter Error"
        }
    }

    /**
     * Memproses frame dari live stream dan mendeteksi gerakan tangan.
     *
     * @param imageProxy Frame gambar dari kamera.
     * @param isFrontCamera Apakah kamera depan yang digunakan.
     */
    @OptIn(ExperimentalGetImage::class)
    fun detectLiveStream(imageProxy: ImageProxy, isFrontCamera: Boolean) {
        when {
            !config.isDetecting -> {
                imageProxy.close()
                Timber.d("SignLanguageRecognizer: isDetecting=false, frame ditutup")
                return
            }

            isProcessing -> {
                imageProxy.close()
                Timber.d("SignLanguageRecognizer: Frame dilewati karena sedang memproses frame sebelumnya")
                return
            }

            else -> {
                isProcessing = true
                frameStartTimeMillis = SystemClock.uptimeMillis()
                currentIsFrontCamera = isFrontCamera

                coroutineScope.launch {
                    try {
                        val bitmap = imageProxy.createToBitmap()
                        imageProxy.close()
                        val rotatedBitmap = bitmap?.let {
                            rotateAndFlipBitmap(
                                it,
                                imageProxy.imageInfo.rotationDegrees,
                                isFrontCamera
                            )
                        }
                        val mpImage = rotatedBitmap?.let { BitmapImageBuilder(it).build() }
                        when {
                            mpImage != null -> {
                                Timber.d("SignLanguageRecognizer: Memproses frame")
                                detectAsync(mpImage, frameStartTimeMillis)
                            }

                            else -> {
                                Timber.e("SignLanguageRecognizer: MPImage null")
                                isProcessing = false
                            }
                        }
                    } catch (e: Exception) {
                        Timber.e(e, "SignLanguageRecognizer: Error during detectLiveStream")
                        imageProxy.close()
                        isProcessing = false
                    }
                }
            }
        }
    }

    /**
     * Mengonversi ImageProxy ke Bitmap menggunakan YuvImage.
     */
    @ExperimentalGetImage
    private fun ImageProxy.createToBitmap(): Bitmap? {
        return try {
            val image = this.image
            image?.let {
                when {
                    format != ImageFormat.YUV_420_888 -> {
                        Timber.e("Unsupported image format: $format")
                        return null
                    }

                    planes.size < 3 -> {
                        Timber.e("Expected 3 planes, but found ${planes.size}")
                        return null
                    }

                    else -> {
                        val yBuffer = planes[0].buffer
                        val uBuffer = planes[1].buffer
                        val vBuffer = planes[2].buffer

                        val ySize = yBuffer.remaining()
                        val uSize = uBuffer.remaining()
                        val vSize = vBuffer.remaining()

                        val nv21 = ByteArray(ySize + uSize + vSize)

                        yBuffer.get(nv21, 0, ySize)

                        vBuffer.get(nv21, ySize, vSize)
                        uBuffer.get(nv21, ySize + vSize, uSize)

                        val yuvImage =
                            YuvImage(nv21, ImageFormat.NV21, this.width, this.height, null)
                        val out = ByteArrayOutputStream()
                        yuvImage.compressToJpeg(Rect(0, 0, this.width, this.height), 100, out)
                        val imageBytes = out.toByteArray()
                        BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                    }
                }
            }
        } catch (e: Exception) {
            Timber.e(e, "Error converting ImageProxy to Bitmap")
            null
        }
    }

    /**
     * Menjalankan deteksi secara asinkron menggunakan HandLandmarker.
     */
    private fun detectAsync(mpImage: MPImage, frameStartTime: Long) {
        handLandmarker?.detectAsync(mpImage, frameStartTime)
    }

    /**
     * Menangani hasil deteksi dari HandLandmarker.
     */
    private fun handleDetectionResult(result: HandLandmarkerResult, input: MPImage) {
        coroutineScope.launch {
            try {
                val inferenceEndTimeMillis = SystemClock.uptimeMillis()
                val inferenceTime = inferenceEndTimeMillis - frameStartTimeMillis
                val landmarksList = result.landmarks()

                Timber.d("handleDetectionResult: Detected ${landmarksList.size} tangan")
                Timber.d("handleDetectionResult: Inference time = $inferenceTime ms")

                val predictions = landmarksList.mapNotNull { landmarks ->
                    val inputArray = preprocessLandmarks(landmarks, currentIsFrontCamera)
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
            } finally {
                isProcessing = false
            }
        }
    }

    /**
     * Preprocessing landmarks untuk inferensi.
     * Membalikkan koordinat X jika kamera depan digunakan.
     */
    private fun preprocessLandmarks(
        landmarks: List<NormalizedLandmark>,
        isFrontCamera: Boolean
    ): FloatArray {
        return landmarks.take(21).flatMap { landmark ->
            when {
                isFrontCamera -> {
                    listOf(landmark.x(), landmark.y())
                }

                else -> {
                    listOf(1f - landmark.x(), landmark.y())
                }
            }
        }.toFloatArray().also { input ->
            input.forEachIndexed { index, value ->
                Timber.d("Preprocess: Landmark ${index / 2} -> ${if (index % 2 == 0) "x" else "y"}=$value")
            }
        }
    }

    /**
     * Memutar dan membalik Bitmap sesuai dengan orientasi dan kamera yang digunakan.
     */
    private fun rotateAndFlipBitmap(
        bitmap: Bitmap,
        rotationDegrees: Int,
        isFrontCamera: Boolean
    ): Bitmap {
        val matrix = Matrix().apply {
            postRotate(rotationDegrees.toFloat())
            when {
                isFrontCamera -> {
                    postScale(-1f, 1f, bitmap.width / 2f, bitmap.height / 2f)
                }
            }
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    /**
     * Menangani error yang terjadi selama proses deteksi.
     */
    private fun handleDetectionError(error: RuntimeException) {
        Timber.e("handleDetectionError: ${error.message}")
        val errorCode = when (config.currentDelegate) {
            DELEGATE_CPU -> OTHER_ERROR
            DELEGATE_GPU -> GPU_ERROR
            else -> OTHER_ERROR
        }
        listener?.onError(error.message ?: "Terjadi error tidak dikenal", errorCode)
    }

    /**
     * Menghentikan live stream dan menutup HandLandmarker.
     */
    private fun stopLiveStream() {
        handLandmarker?.close()
        handLandmarker = null
        config.isDetecting = false
        Timber.d("SignLanguageRecognizer: Live stream stopped")
    }

    /**
     * Menutup semua resource yang digunakan.
     */
    override fun close() {
        handLandmarker?.close()
        interpreter?.close()
        handLandmarker = null
        interpreter = null
        stopLiveStream()
        Timber.d("SignLanguageRecognizer: Closed and resources cleaned up")
    }

    /**
     * Menghentikan proses terjemahan.
     */
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
        const val DELEGATE_GPU = 1
        const val DEFAULT_HAND_DETECTION_CONFIDENCE = 0.5F
        const val DEFAULT_HAND_TRACKING_CONFIDENCE = 0.5F
        const val DEFAULT_HAND_PRESENCE_CONFIDENCE = 0.5F
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
        var tfliteModelAssetPath: String = MODEL_ISYARA
    )
}

/**
 * Extension function untuk mengecek apakah sebuah karakter adalah huruf.
 */
private fun Char.isLetter(): Boolean {
    return this in 'A'..'Z' || this in 'a'..'z'
}
