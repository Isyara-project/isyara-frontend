package com.application.isyara.data.di

import com.application.isyara.data.local.SessionManager
import com.application.isyara.data.local.UserPreferences
import com.application.isyara.data.remote.ApiService
import com.application.isyara.data.repository.AuthRepository
import com.application.isyara.data.repository.ChangePasswordRepository
import com.application.isyara.data.repository.FeedbackRepository
import com.application.isyara.data.repository.PasswordRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
        @RetrofitMain apiService: ApiService
    ): ChangePasswordRepository {
        return ChangePasswordRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()
}
