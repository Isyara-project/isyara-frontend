package com.application.isyara.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DownloadedDictionary::class, TranslatedText::class, DownloadedDictionaryPicture::class],
    version = 4,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun downloadedDictionaryDao(): DownloadedDictionaryDao
    abstract fun translatedTextDao(): TranslatedTextDao
    abstract fun downloadDictionaryPictureDao(): DownloadDictionaryPictureDao

    companion object {
        const val DATABASE_NAME = "isyara_database"
    }
}
