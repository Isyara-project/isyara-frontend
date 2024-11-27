package com.application.isyara.data.di

import com.application.isyara.data.repository.AuthRepository
import com.application.isyara.data.remote.AuthApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideAuthRepository(authApiService: AuthApiService): AuthRepository {
        return AuthRepository(authApiService)
    }
}
