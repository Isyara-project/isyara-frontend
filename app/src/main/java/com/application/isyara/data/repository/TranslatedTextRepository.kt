package com.application.isyara.data.repository

import android.content.Context
import com.application.isyara.data.local.TranslatedText
import com.application.isyara.data.local.TranslatedTextDao
import com.application.isyara.utils.translate.Dictionary
import com.application.isyara.utils.translate.WordBreaker
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import com.application.isyara.utils.state.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TranslatedTextRepository @Inject constructor(
    private val translatedTextDao: TranslatedTextDao,
    @ApplicationContext private val context: Context
) {
    private lateinit var dictionary: Dictionary
    private lateinit var wordBreaker: WordBreaker

    /**
     * Memuat kamus dari assets dan menginisialisasi WordBreaker.
     */
    suspend fun initializeDictionary(): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                dictionary = Dictionary.loadFromAssets(context, "indonesia_dictionary.txt")
                wordBreaker = WordBreaker(dictionary)
                Timber.d("TranslatedTextRepository: Kamus Bahasa Indonesia berhasil dimuat.")
                Result.Success(Unit)
            } catch (e: Exception) {
                Timber.e(e, "TranslatedTextRepository: Gagal memuat kamus Bahasa Indonesia.")
                Result.Error("Gagal memuat kamus Bahasa Indonesia", e.hashCode())
            }
        }
    }

    /**
     * Mengembalikan instance WordBreaker.
     */
    fun getWordBreaker(): WordBreaker {
        return wordBreaker
    }

    /**
     * Menyimpan teks terjemahan ke database.
     * @param translatedText Objek TranslatedText yang akan disimpan.
     */
    suspend fun insertTranslatedText(translatedText: TranslatedText): Result<Unit> {
        return try {
            translatedTextDao.insertTranslatedText(translatedText)
            Timber.d("TranslatedTextRepository: Teks terjemahan berhasil disimpan.")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "TranslatedTextRepository: Gagal menyimpan teks terjemahan.")
            Result.Error("Gagal menyimpan teks terjemahan", e.hashCode())
        }
    }

    /**
     * Mengambil teks terjemahan terbaru dari database.
     */
    suspend fun getLatestTranslatedText(): Result<TranslatedText?> {
        return try {
            val latest = translatedTextDao.getLatestTranslatedText()
            if (latest != null) {
                Timber.d("TranslatedTextRepository: Teks terjemahan terbaru ditemukan.")
                Result.Success(latest)
            } else {
                Timber.d("TranslatedTextRepository: Tidak ada teks terjemahan ditemukan.")
                Result.Success(null)
            }
        } catch (e: Exception) {
            Timber.e(e, "TranslatedTextRepository: Gagal mengambil teks terjemahan terbaru.")
            Result.Error("Gagal mengambil teks terjemahan terbaru", e.hashCode())
        }
    }

    /**
     * Menambahkan kata kustom ke kamus.
     * @param word Kata kustom yang ingin ditambahkan.
     */
    fun addCustomWord(word: String): Result<Unit> {
        return try {
            dictionary.insert(word)
            wordBreaker.insert(word)
            Timber.d("TranslatedTextRepository: Kata kustom '$word' berhasil ditambahkan ke kamus.")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "TranslatedTextRepository: Gagal menambahkan kata kustom '$word'.")
            Result.Error("Gagal menambahkan kata kustom", e.hashCode())
        }
    }

    /**
     * Menghapus kata kustom dari kamus.
     * @param word Kata kustom yang ingin dihapus.
     */
    fun deleteCustomWord(word: String): Result<Unit> {
        return try {
            dictionary.remove(word)
            wordBreaker.remove(word)
            Timber.d("TranslatedTextRepository: Kata kustom '$word' berhasil dihapus dari kamus.")
            Result.Success(Unit)
        } catch (e: Exception) {
            Timber.e(e, "TranslatedTextRepository: Gagal menghapus kata kustom '$word'.")
            Result.Error("Gagal menghapus kata kustom", e.hashCode())
        }
    }

    suspend fun deleteAllTranslatedTexts() {
        translatedTextDao.deleteAllTranslatedTexts()
    }

    suspend fun deleteTranslatedTextById(id: Int) {
        translatedTextDao.deleteTranslatedTextById(id)
    }

    fun getAllTranslatedTexts(): Flow<List<TranslatedText>> =
        translatedTextDao.getAllTranslatedTexts()
}
