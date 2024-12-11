package com.application.isyara.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DownloadedDictionaryDao {

    @Query("SELECT * FROM downloaded_dictionary")
    fun getAllDownloadedItems(): Flow<List<DownloadedDictionary>>

    @Query("SELECT * FROM downloaded_dictionary WHERE url = :url LIMIT 1")
    suspend fun getDownloadedItemByUrl(url: String): DownloadedDictionary?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDownloadedItem(item: DownloadedDictionary)

    @Delete
    suspend fun deleteDownloadedItem(item: DownloadedDictionary)

    @Query("DELETE FROM downloaded_dictionary")
    suspend fun deleteAllDownloadedItems()
}
