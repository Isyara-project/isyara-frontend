@file:OptIn(FlowPreview::class)

package com.application.isyara.viewmodel.translate

import android.content.Context
import android.os.SystemClock
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.local.TranslatedText
import com.application.isyara.data.repository.ModelDownloadRepository
import com.application.isyara.data.repository.TranslatedTextRepository
import com.application.isyara.utils.state.Result
import com.application.isyara.utils.translate.LandmarkerListener
import com.application.isyara.utils.translate.ResultBundle
import com.application.isyara.utils.translate.SignLanguageRecognizer
import com.application.isyara.utils.translate.WordBreaker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(
    private val translatedTextRepository: TranslatedTextRepository,
    private val modelDownloadRepository: ModelDownloadRepository,
    @ApplicationContext private val context: Context
) : ViewModel(), LandmarkerListener {

    private val mediaPipeUrl =
        "https://storage.googleapis.com/isyara_kamus_bucket/tools/hand_landmarker.task"
    private val tfLiteUrl = "https://storage.googleapis.com/isyara_kamus_bucket/model/isyara.tflite"
    private val mediaPipeFile = "hand_landmarker.task"
    private val tfLiteFile = "isyara.tflite"

    private val _downloadProgress = MutableStateFlow<Int>(0)
    val downloadProgress: StateFlow<Int> = _downloadProgress.asStateFlow()

    private val _isModelDownloading = MutableStateFlow(false)
    val isModelDownloading: StateFlow<Boolean> = _isModelDownloading.asStateFlow()

    private val _predictionState = MutableStateFlow<Result<String>>(Result.Idle)
    val predictionState: StateFlow<Result<String>> = _predictionState.asStateFlow()

    private val _isTranslationActive = MutableStateFlow(false)
    val isTranslationActive: StateFlow<Boolean> = _isTranslationActive.asStateFlow()

    private val translatedText = StringBuilder()
    private lateinit var wordBreaker: WordBreaker

    private val _isDictionaryLoaded = MutableStateFlow(false)
    val isDictionaryLoaded: StateFlow<Boolean> = _isDictionaryLoaded.asStateFlow()

    internal var signLanguageRecognizer: SignLanguageRecognizer? = null

    private val predictionFlow = MutableSharedFlow<String>(
        extraBufferCapacity = 100,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    init {
        viewModelScope.launch {
            _predictionState.value = Result.Loading
            when (val initResult = translatedTextRepository.initializeDictionary()) {
                is Result.Success -> {
                    wordBreaker = translatedTextRepository.getWordBreaker()
                    _isDictionaryLoaded.value = true
                    _predictionState.value =
                        Result.Success("Kamus Bahasa Indonesia berhasil dimuat.")
                    Timber.d("TranslateViewModel: Kamus Bahasa Indonesia berhasil dimuat.")
                }

                is Result.Error -> {
                    _predictionState.value = Result.Error(initResult.message, initResult.errorCode)
                }

                else -> {
                    _predictionState.value =
                        Result.Error("Unknown error during initialization", null)
                }
            }
            downloadModelsIfNeeded()
        }

        viewModelScope.launch {
            predictionFlow
                .throttleLatest(1500)
                .debounce(50)
                .collect { prediction ->
                    processPrediction(prediction)
                }
        }
    }


    private suspend fun downloadModelsIfNeeded() {
        try {
            _isModelDownloading.value = true

            if (!modelDownloadRepository.isModelDownloaded(mediaPipeFile)) {
                Timber.d("TranslateViewModel: Mengunduh MediaPipe model...")
                modelDownloadRepository.downloadModel(
                    mediaPipeUrl,
                    mediaPipeFile
                ) { progress ->
                    _downloadProgress.value = progress
                }
                Timber.d("TranslateViewModel: MediaPipe model berhasil diunduh.")
            } else {
                Timber.d("TranslateViewModel: MediaPipe model sudah ada.")
            }

            if (!modelDownloadRepository.isModelDownloaded(tfLiteFile)) {
                Timber.d("TranslateViewModel: Mengunduh TensorFlow Lite model...")
                modelDownloadRepository.downloadModel(
                    tfLiteUrl,
                    tfLiteFile
                ) { progress ->
                    _downloadProgress.value = progress
                }
                Timber.d("TranslateViewModel: TensorFlow Lite model berhasil diunduh.")
            } else {
                Timber.d("TranslateViewModel: TensorFlow Lite model sudah ada.")
            }

            _isModelDownloading.value = false
            _downloadProgress.value = 0

            if (!_isTranslationActive.value) {
                signLanguageRecognizer = SignLanguageRecognizer(
                    context = context,
                    listener = this@TranslateViewModel,
                    config = SignLanguageRecognizer.Config(
                        isDetecting = true,
                        modelAssetPath = mediaPipeFile,
                        tfliteModelAssetPath = tfLiteFile,
                        currentDelegate = SignLanguageRecognizer.DELEGATE_CPU
                    )
                )
                Timber.d("TranslateViewModel: SignLanguageRecognizer diinisialisasi.")
            }
        } catch (e: Exception) {
            _isModelDownloading.value = false
            _downloadProgress.value = 0
            Timber.e(e, "TranslateViewModel: Gagal mengunduh model")
            _predictionState.value = Result.Error("Gagal mengunduh model: ${e.message}", -1)
        }
    }


    /**
     * Callback yang dipanggil ketika hasil deteksi bahasa isyarat tersedia.
     */
    override fun onResults(resultBundle: ResultBundle) {
        viewModelScope.launch {
            if (resultBundle.prediction.isNotEmpty()) {
                val predictionLower = resultBundle.prediction.lowercase(Locale.getDefault())
                predictionFlow.emit(predictionLower)
            }
        }
    }

    /**
     * Callback yang dipanggil ketika terjadi kesalahan dalam deteksi bahasa isyarat.
     */
    override fun onError(error: String, errorCode: Int) {
        viewModelScope.launch {
            Timber.e("TranslateViewModel: onError dipanggil dengan error: $error")
            _predictionState.value = Result.Error(error, errorCode)
        }
    }

    /**
     * Mengaktifkan atau menonaktifkan translasi.
     * @param active Status aktifasi translasi.
     */
    fun setTranslationActive(active: Boolean) {
        viewModelScope.launch {
            if (active) {
                if (_isDictionaryLoaded.value) {
                    if (!_isTranslationActive.value) {
                        Timber.d("TranslateViewModel: Translation activated")
                        translatedText.clear()
                        _predictionState.value = Result.Loading
                        _isTranslationActive.value = true
                        signLanguageRecognizer = SignLanguageRecognizer(
                            context = context,
                            listener = this@TranslateViewModel,
                            config = SignLanguageRecognizer.Config(
                                isDetecting = true,
                                modelAssetPath = mediaPipeFile,
                                tfliteModelAssetPath = tfLiteFile,
                                currentDelegate = SignLanguageRecognizer.DELEGATE_CPU
                            )
                        )
                        Timber.d("TranslateViewModel: SignLanguageRecognizer diinisialisasi saat aktivasi translasi.")
                    } else {
                        Timber.d("TranslateViewModel: Translation sudah aktif")
                        Toast.makeText(
                            context,
                            "Translasi sudah aktif",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Timber.d("TranslateViewModel: Kamus belum dimuat, tidak bisa memulai translasi")
                    _predictionState.value = Result.Error("Kamus belum dimuat", -1)
                }
            } else {
                if (_isTranslationActive.value) {
                    Timber.d("TranslateViewModel: Translation deactivated")
                    _isTranslationActive.value = false
                    signLanguageRecognizer?.stopTranslation()
                    signLanguageRecognizer?.close()
                    signLanguageRecognizer = null
                    saveTranslatedText()
                } else {
                    Timber.d("TranslateViewModel: Translation tidak aktif")
                }
            }
        }
    }

    /**
     * Menyimpan teks terjemahan yang telah dihasilkan ke database.
     */
    private suspend fun saveTranslatedText() {
        if (translatedText.isNotEmpty()) {
            val text = translatedText.toString()
            _predictionState.value = Result.Loading
            when (val result =
                translatedTextRepository.insertTranslatedText(TranslatedText(text = text))) {
                is Result.Success -> {
                    Timber.d("TranslateViewModel: Teks terjemahan berhasil disimpan: $text")
                    loadLatestTranslatedText()
                }

                is Result.Error -> {
                    _predictionState.value = Result.Error(
                        result.message,
                        result.errorCode
                    )
                }

                else -> {}
            }
        } else {
            Timber.d("TranslateViewModel: Tidak ada teks untuk disimpan")
            _predictionState.value = Result.Idle
        }
    }

    /**
     * Memuat teks terjemahan terbaru dari database.
     */
    private suspend fun loadLatestTranslatedText() {
        when (val result = translatedTextRepository.getLatestTranslatedText()) {
            is Result.Success -> {
                val latestText = result.data
                if (latestText != null) {
                    _predictionState.value = Result.Success(latestText.text)
                    Timber.d("TranslateViewModel: Teks terjemahan terbaru dimuat: ${latestText.text}")
                } else {
                    _predictionState.value = Result.Idle
                    Timber.d("TranslateViewModel: Tidak ada teks terjemahan ditemukan di database")
                }
            }

            is Result.Error -> {
                _predictionState.value = Result.Error(
                    result.message,
                    result.errorCode
                )
            }

            else -> {}
        }
    }

    /**
     * Mengatur ulang teks terjemahan yang sedang diproses.
     */
    fun resetTranslation() {
        viewModelScope.launch {
            translatedText.clear()
            _predictionState.value = Result.Idle
            Timber.d("TranslateViewModel: Translasi direset")
        }
    }

    override fun onCleared() {
        super.onCleared()
        signLanguageRecognizer?.close()
    }

    /**
     * Memproses prediksi dengan menambahkan ke teks terjemahan dan memperbarui UI.
     */
    private fun processPrediction(prediction: String) {
        val startTime = SystemClock.uptimeMillis()
        translatedText.append(prediction)
        val inputText = translatedText.toString()
        Timber.d("TranslateViewModel: TranslatedText setelah append: $inputText")
        if (::wordBreaker.isInitialized) {
            val assembledSentence = wordBreaker.breakWords(inputText)
            Timber.d("TranslateViewModel: assembledSentence: $assembledSentence")
            _predictionState.value = Result.Success(assembledSentence)
        } else {
            Timber.e("TranslateViewModel: WordBreaker belum diinisialisasi.")
            _predictionState.value = Result.Error("Kamus belum dimuat", -1)
        }
        val endTime = SystemClock.uptimeMillis()
        val duration = endTime - startTime
        Timber.d("TranslateViewModel: processPrediction took $duration ms")
    }


    /**
     * Extension function untuk melakukan throttleLatest pada SharedFlow.
     * Membatasi seberapa sering kolektor dapat menerima emisi.
     */
    private fun <T> Flow<T>.throttleLatest(delayMillis: Long): Flow<T> = flow {
        var lastEmissionTime = 0L
        collect { value ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastEmissionTime >= delayMillis) {
                emit(value)
                lastEmissionTime = currentTime
            }
        }
    }
}
