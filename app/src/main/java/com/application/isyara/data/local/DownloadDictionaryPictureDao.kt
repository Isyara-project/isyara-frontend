package com.application.isyara.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadDictionaryPictureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(pictures: List<DownloadedDictionaryPicture>)

    @Query("SELECT * FROM downloaded_dictionary_pictures")
    fun getAllPictures(): Flow<List<DownloadedDictionaryPicture>>

    @Query("SELECT * FROM downloaded_dictionary_pictures WHERE url = :url LIMIT 1")
    suspend fun getDownloadedPictureByUrl(url: String): DownloadedDictionaryPicture?

    @Query("DELETE FROM downloaded_dictionary_pictures WHERE url = :url")
    suspend fun deleteDownloadedPictureByUrl(url: String)

    @Query("DELETE FROM downloaded_dictionary_pictures")
    suspend fun deleteAllPictures()
}
