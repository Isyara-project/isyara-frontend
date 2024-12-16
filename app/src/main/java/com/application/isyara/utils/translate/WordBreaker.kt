package com.application.isyara.utils.translate

import timber.log.Timber
import java.util.Locale

class WordBreaker(private val dictionary: Dictionary) {

    private val cache = mutableMapOf<String, Boolean>()

    /**
     * Memecah string input menjadi kalimat dengan spasi yang tepat berdasarkan kamus.
     * Contoh: "halodunia" akan dipisah menjadi "halo dunia"
     */
    fun breakWords(s: String): String {
        val n = s.length
        val dp = BooleanArray(n + 1)
        val prev = IntArray(n + 1) { -1 }
        dp[0] = true

        for (i in 1..n) {
            for (j in 0 until i) {
                if (dp[j] && isWordCached(s.substring(j, i))) {
                    dp[i] = true
                    prev[i] = j
                    break
                }
            }
        }

        if (!dp[n]) {
            Timber.d("WordBreaker: Tidak dapat menyusun kalimat dengan benar.")
        }

        val words = mutableListOf<String>()
        var end = n
        while (end > 0) {
            val start = prev[end]
            if (start == -1) {
                // Jika tidak dapat dipisah, tambahkan karakter terakhir
                words.add(s.substring(end - 1, end))
                end -= 1
            } else {
                words.add(s.substring(start, end))
                end = start
            }
        }

        val result = words.reversed().joinToString(" ")
        Timber.d("WordBreaker: Input='$s' -> Output='$result'")
        return result
    }

    /**
     * Mengecek apakah kata ada dalam kamus dengan menggunakan cache untuk optimasi.
     */
    private fun isWordCached(word: String): Boolean {
        return cache[word] ?: run {
            val result = dictionary.isWord(word)
            cache[word] = result
            result
        }
    }

    /**
     * Menambahkan kata ke kamus dan cache.
     */
    fun insert(word: String) {
        dictionary.insert(word)
        cache[word.lowercase(Locale.getDefault())] = true
    }
}
