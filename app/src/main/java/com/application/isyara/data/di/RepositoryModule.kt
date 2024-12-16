package com.application.isyara.data.di

import android.content.Context
import com.application.isyara.data.preferences.SessionManager
import com.application.isyara.data.preferences.UserPreferences
import com.application.isyara.data.remote.ApiService
import com.application.isyara.data.repository.AuthRepository
import com.application.isyara.data.repository.ChangePasswordRepository
import com.application.isyara.data.repository.FeedbackRepository
import com.application.isyara.data.repository.ModelDownloadRepository
import com.application.isyara.data.repository.PasswordRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        @RetrofitMain apiService: ApiService,
        sessionManager: SessionManager,
        userPreferences: UserPreferences
    ): AuthRepository {
        return AuthRepository(apiService, sessionManager, userPreferences)
    }

    @Provides
    @Singleton
    fun providePasswordRepository(
        @RetrofitMain apiService: ApiService
    ): PasswordRepository {
        return PasswordRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideFeedbackRepository(
        @RetrofitMain apiService: ApiService
    ): FeedbackRepository {
        return FeedbackRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideChangePasswordRepository(
        @RetrofitMain apiService: ApiService,
        sessionManager: SessionManager
    ): ChangePasswordRepository {
        return ChangePasswordRepository(apiService, sessionManager)
    }

    @Provides
    @Singleton
    fun provideModelDownloadRepository(
        @ApplicationContext context: Context
    ): ModelDownloadRepository {
        return ModelDownloadRepository(context)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()
}
