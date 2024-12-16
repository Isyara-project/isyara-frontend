package com.application.isyara.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloaded_dictionary_pictures")
data class DownloadedDictionaryPicture(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val url: String,
    val localPath: String,
    val timestamp: Long = System.currentTimeMillis()
)