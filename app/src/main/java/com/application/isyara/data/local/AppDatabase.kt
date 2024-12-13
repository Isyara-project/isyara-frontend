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
        const val DATABASE_NAME = "isyara_database"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    """
                        CREATE TABLE IF NOT EXISTS downloaded_dictionary_pictures_new (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            url TEXT NOT NULL
                        )
                    """.trimIndent()
                )

                database.execSQL(
                    """
                        INSERT INTO downloaded_dictionary_pictures_new (id, url)
                        SELECT id, imageUrl FROM downloaded_dictionary_pictures
                    """.trimIndent()
                )

                database.execSQL("DROP TABLE downloaded_dictionary_pictures")

                database.execSQL("ALTER TABLE downloaded_dictionary_pictures_new RENAME TO downloaded_dictionary_pictures")
            }
        }
    }
}
