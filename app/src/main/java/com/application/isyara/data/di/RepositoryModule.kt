package com.application.isyara.data.di

import com.application.isyara.data.local.SessionManager
import com.application.isyara.data.remote.ApiService
import com.application.isyara.data.repository.AuthRepository
import com.application.isyara.data.repository.PasswordRepository
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
        sessionManager: SessionManager
    ): AuthRepository {
        return AuthRepository(apiService, sessionManager)
    }

    @Provides
    @Singleton
    fun providePasswordRepository(
        @RetrofitMain apiService: ApiService
    ): PasswordRepository {
        return PasswordRepository(apiService)
    }

}
