package com.application.isyara.utils.translate

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.Locale

class Dictionary(private val trie: Trie) {

    /**
     * Menambahkan kata ke kamus.
     */
    fun insert(word: String) {
        trie.insert(word.lowercase(Locale.getDefault()))
    }

    /**
     * Mengecek apakah kata ada dalam kamus.
     */
    fun isWord(word: String): Boolean {
        return trie.search(word.lowercase(Locale.getDefault()))
    }

    /**
     * Mengecek apakah ada kata yang dimulai dengan prefix tertentu.
     */
    fun startsWith(prefix: String): Boolean {
        return trie.startsWith(prefix.lowercase(Locale.getDefault()))
    }

    /**
     * Menghapus kata dari kamus.
     */
    fun remove(word: String) {
        trie.remove(word.lowercase(Locale.getDefault()))
    }

    companion object {
        /**
         * Memuat kamus dari file assets.
         * @param context Context untuk mengakses assets.
         * @param assetFileName Nama file kamus di assets.
         * @return Instance Dictionary.
         */
        suspend fun loadFromAssets(context: Context, assetFileName: String): Dictionary {
            return withContext(Dispatchers.IO) {
                val trie = Trie()
                try {
                    context.assets.open(assetFileName).use { inputStream ->
                        inputStream.bufferedReader().useLines { lines ->
                            lines.forEach { line ->
                                val word = line.trim().lowercase(Locale.getDefault())
                                if (word.isNotEmpty()) {
                                    trie.insert(word)
                                }
                            }
                        }
                    }
                    Timber.d("Dictionary: Kamus berhasil dimuat dari $assetFileName")
                } catch (e: Exception) {
                    Timber.e(e, "Dictionary: Gagal memuat kamus dari $assetFileName")
                    throw e
                }
                Dictionary(trie)
            }
        }
    }
}
