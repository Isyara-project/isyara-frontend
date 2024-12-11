package com.application.isyara.utils.translate

data class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    var isEndOfWord: Boolean = false
)