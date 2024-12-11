package com.application.isyara.utils.translate

class Trie {
    private class TrieNode {
        val children: MutableMap<Char, TrieNode> = mutableMapOf()
        var isEndOfWord: Boolean = false
    }

    private val root = TrieNode()

    /**
     * Menambahkan kata ke Trie.
     */
    fun insert(word: String) {
        var current = root
        for (char in word) {
            current = current.children.getOrPut(char) { TrieNode() }
        }
        current.isEndOfWord = true
    }

    /**
     * Mencari apakah kata ada dalam Trie.
     */
    fun search(word: String): Boolean {
        var current = root
        for (char in word) {
            current = current.children[char] ?: return false
        }
        return current.isEndOfWord
    }

    /**
     * Mengecek apakah ada kata yang dimulai dengan prefix tertentu.
     */
    fun startsWith(prefix: String): Boolean {
        var current = root
        for (char in prefix) {
            current = current.children[char] ?: return false
        }
        return true
    }

    /**
     * Menghapus kata dari Trie.
     * @return True jika node dapat dihapus, False jika tidak.
     */
    fun remove(word: String): Boolean {
        return remove(root, word, 0)
    }

    /**
     * Helper rekursif untuk menghapus kata dari Trie.
     */
    private fun remove(current: TrieNode, word: String, index: Int): Boolean {
        if (index == word.length) {
            if (!current.isEndOfWord) {
                return false
            }
            current.isEndOfWord = false
            return current.children.isEmpty()
        }
        val char = word[index]
        val node = current.children[char] ?: return false
        val shouldDeleteCurrentNode = remove(node, word, index + 1)

        if (shouldDeleteCurrentNode) {
            current.children.remove(char)
            return current.children.isEmpty() && !current.isEndOfWord
        }
        return false
    }
}
