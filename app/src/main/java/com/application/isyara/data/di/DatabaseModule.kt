package com.application.isyara.data.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.application.isyara.data.local.AppDatabase
import com.application.isyara.data.local.AppDatabase.Companion.DATABASE_NAME
import com.application.isyara.data.local.DownloadDictionaryPictureDao
import com.application.isyara.data.local.DownloadedDictionaryDao
import com.application.isyara.data.local.TranslatedTextDao
import com.application.isyara.data.remote.ApiService
import com.application.isyara.data.repository.DictionaryPictureRepository
import com.application.isyara.data.repository.DictionaryRepository
import com.application.isyara.data.repository.TranslatedTextRepository
import com.application.isyara.utils.dictionary.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE downloaded_dictionary_pictures ADD COLUMN localPath TEXT NOT NULL DEFAULT 'undefined'")
            database.execSQL("ALTER TABLE downloaded_dictionary_pictures ADD COLUMN timestamp INTEGER NOT NULL DEFAULT 0")
        }
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .addMigrations(MIGRATION_3_4)
            .build()
    }

    @Singleton
    @Provides
    fun provideDownloadedDictionaryDao(database: AppDatabase): DownloadedDictionaryDao {
        return database.downloadedDictionaryDao()
    }


    @Singleton
    @Provides
    fun provideTranslatedTextDao(database: AppDatabase): TranslatedTextDao {
        return database.translatedTextDao()
    }

    @Singleton
    @Provides
    fun provideDownloadDictionaryPictureDao(database: AppDatabase): DownloadDictionaryPictureDao {
        return database.downloadDictionaryPictureDao()
    }

    @Provides
    @Singleton
    fun provideTranslatedTextRepository(
        translatedTextDao: TranslatedTextDao,
        @ApplicationContext context: Context
    ): TranslatedTextRepository {
        return TranslatedTextRepository(translatedTextDao, context)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelper(context)
    }

    @Provides
    @Singleton
    fun provideDictionaryRepository(
        @RetrofitDictionary apiService: ApiService,
        @ApplicationContext context: Context,
        downloadedDictionaryDao: DownloadedDictionaryDao
    ): DictionaryRepository {
        return DictionaryRepository(apiService, context, downloadedDictionaryDao)
    }

    @Provides
    @Singleton
    fun provideDictionaryPictureRepository(
        @RetrofitDictionary apiService: ApiService,
        downloadDictionaryPictureDao: DownloadDictionaryPictureDao,
        networkHelper: NetworkHelper
    ): DictionaryPictureRepository {
        return DictionaryPictureRepository(apiService, downloadDictionaryPictureDao, networkHelper)
    }
}
