package com.application.isyara.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translated_texts")
data class TranslatedText(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val timestamp: Long = System.currentTimeMillis()
)
