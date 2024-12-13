package com.application.isyara.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DownloadDictionaryPictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadedPicture(picture: DownloadedDictionaryPicture)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPictures(pictures: List<DownloadedDictionaryPicture>)

    @Query("SELECT * FROM downloaded_dictionary_pictures")
    suspend fun getAllDownloadedPictures(): List<DownloadedDictionaryPicture>

    @Query("SELECT * FROM downloaded_dictionary_pictures WHERE imageUrl = :url")
    suspend fun getDownloadedPictureByUrl(url: String): DownloadedDictionaryPicture?

    @Query("DELETE FROM downloaded_dictionary_pictures WHERE imageUrl = :url")
    suspend fun deleteDownloadedPictureByUrl(url: String)
}
