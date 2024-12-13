package com.application.isyara.data.di

import android.content.Context
import androidx.room.Room
import com.application.isyara.data.local.AppDatabase
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

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "isyara_database"
        )
            .addMigrations(AppDatabase.MIGRATION_1_2)
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
