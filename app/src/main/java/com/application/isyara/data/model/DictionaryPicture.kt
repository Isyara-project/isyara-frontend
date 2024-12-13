package com.application.isyara.data.model

import com.application.isyara.data.local.DownloadedDictionaryPicture

data class DictionaryPicture(
    val word: String,
    val imageUrl: String,
    val definition: String
)

fun DownloadedDictionaryPicture.toDictionaryPicture(): DictionaryPicture {
    return DictionaryPicture(imageUrl, word, definition)
}
