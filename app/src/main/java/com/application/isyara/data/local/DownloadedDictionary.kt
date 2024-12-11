package com.application.isyara.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloaded_dictionary")
data class DownloadedDictionary(
    @PrimaryKey val url: String,
    val title: String,
    val localPath: String,
    val imageUrl: String? = null,
    val localImagePath: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)
