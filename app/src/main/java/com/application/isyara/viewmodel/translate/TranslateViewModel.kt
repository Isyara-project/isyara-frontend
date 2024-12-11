package com.application.isyara.viewmodel.translate

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.local.TranslatedText
import com.application.isyara.data.repository.TranslatedTextRepository
import com.application.isyara.utils.state.Result
import com.application.isyara.utils.translate.LandmarkerListener
import com.application.isyara.utils.translate.ResultBundle
import com.application.isyara.utils.translate.SignLanguageRecognizer
import com.application.isyara.utils.translate.WordBreaker
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class TranslateViewModel @Inject constructor(
    private val translatedTextRepository: TranslatedTextRepository,
    @ApplicationContext private val context: Context
) : ViewModel(), LandmarkerListener {

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
        }

        viewModelScope.launch {
            predictionFlow
                .throttleLatest(1500)
                .collect { prediction ->
                    processPrediction(prediction)
                }
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
                        // Menginisialisasi SignLanguageRecognizer baru
                        signLanguageRecognizer = SignLanguageRecognizer(
                            context = context,
                            listener = this@TranslateViewModel,
                            config = SignLanguageRecognizer.Config(
                                isDetecting = true
                            )
                        )
                    } else {
                        Timber.d("TranslateViewModel: Translation sudah aktif")
                        // Opsional: Tampilkan pesan kepada pengguna bahwa translasi sudah aktif
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
                    // Menghentikan dan menutup SignLanguageRecognizer
                    signLanguageRecognizer?.stopTranslation()
                    signLanguageRecognizer?.close()
                    signLanguageRecognizer = null
                    saveTranslatedText()
                } else {
                    Timber.d("TranslateViewModel: Translation tidak aktif")
                    // Opsional: Tampilkan pesan kepada pengguna bahwa translasi tidak aktif
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
                        result.message ?: "Gagal menyimpan teks terjemahan",
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
                    result.message ?: "Gagal mengambil teks terjemahan",
                    result.errorCode
                )
            }

            else -> {}
        }
    }

    /**
     * Menambahkan kata kustom ke kamus.
     * @param word Kata kustom yang ingin ditambahkan.
     */
    fun addCustomWord(word: String) {
        viewModelScope.launch {
            when (val result = translatedTextRepository.addCustomWord(word)) {
                is Result.Success -> {
                    Timber.d("TranslateViewModel: Kata kustom '$word' berhasil ditambahkan.")
                    Toast.makeText(
                        context,
                        "Kata '$word' berhasil ditambahkan",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Result.Error -> {
                    Timber.e("TranslateViewModel: Gagal menambahkan kata kustom '$word'.")
                    _predictionState.value = Result.Error(
                        result.message ?: "Gagal menambahkan kata kustom",
                        result.errorCode
                    )
                    Toast.makeText(
                        context,
                        result.message ?: "Gagal menambahkan kata kustom",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
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
        // Menutup SignLanguageRecognizer jika masih ada
        signLanguageRecognizer?.close()
    }

    /**
     * Memproses prediksi dengan menambahkan ke teks terjemahan dan memperbarui UI.
     */
    private suspend fun processPrediction(prediction: String) {
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
