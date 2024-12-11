// src/test/java/com/application/isyara/utils/WordBreakerTest.kt
package com.application.isyara.utils.translate

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WordBreakerTest {

    private lateinit var dictionary: Dictionary
    private lateinit var wordBreaker: WordBreaker

    @Before
    fun setup() = runBlocking {
        val trie = Trie()
        val words = listOf(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
            "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "halo", "dunia", "android", "kotlin", "developer"
        )
        words.forEach { trie.insert(it) }
        dictionary = Dictionary(trie)
        wordBreaker = WordBreaker(dictionary)
    }

    @Test
    fun testBreakWordsSingleLetters() {
        val input = "abc"
        val expected = "a b c"
        val result = wordBreaker.breakWords(input)
        assertEquals(expected, result)
    }

    @Test
    fun testBreakWordsMixed() {
        val input = "haloduniaandroidkotlindeveloper"
        val expected = "halo dunia android kotlin developer"
        val result = wordBreaker.breakWords(input)
        assertEquals(expected, result)
    }

    @Test
    fun testBreakWordsWithUnknownWord() {
        val input = "haloduniaandroidkotlindeveloperxyz"
        val expected = "halo dunia android kotlin developer x y z"
        val result = wordBreaker.breakWords(input)
        assertEquals(expected, result)
    }
}
