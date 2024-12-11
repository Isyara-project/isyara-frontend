package com.application.isyara.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TranslatedTextDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTranslatedText(translatedText: TranslatedText)

    @Query("SELECT * FROM translated_texts ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestTranslatedText(): TranslatedText?

    @Query("SELECT * FROM translated_texts ORDER BY timestamp DESC")
    fun getAllTranslatedTexts(): Flow<List<TranslatedText>>

    @Query("DELETE FROM translated_texts")
    suspend fun deleteAllTranslatedTexts()

    @Query("DELETE FROM translated_texts WHERE id = :id")
    suspend fun deleteTranslatedTextById(id: Int)
}
