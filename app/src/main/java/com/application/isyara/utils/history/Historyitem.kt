package com.application.isyara.utils.history

sealed class HistoryItem {
    abstract val timestamp: Long

    data class Translation(
        val id: Int,
        val text: String,
        override val timestamp: Long
    ) : HistoryItem()

    data class DownloadedDictionary(
        val url: String,
        val title: String,
        val localPath: String,
        val imageUrl: String? = null,
        val localImagePath: String? = null,
        override val timestamp: Long
    ) : HistoryItem()


}
