package com.application.isyara.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [DownloadedDictionary::class, TranslatedText::class, DownloadedDictionaryPicture::class],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun downloadedDictionaryDao(): DownloadedDictionaryDao
    abstract fun translatedTextDao(): TranslatedTextDao
    abstract fun downloadDictionaryPictureDao(): DownloadDictionaryPictureDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
            CREATE TABLE IF NOT EXISTS translated_texts (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                text TEXT NOT NULL,
                timestamp INTEGER NOT NULL
            )
        """.trimIndent()
                )

                database.execSQL(
                    """
            CREATE TABLE IF NOT EXISTS downloaded_dictionary_pictures (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                imageUrl TEXT NOT NULL
            )
        """.trimIndent()
                )
            }
        }

    }
}
