package com.application.isyara.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloaded_dictionary_pictures")
data class DownloadedDictionaryPicture(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val word: String,
    val imageUrl: String,
    val definition: String
)
