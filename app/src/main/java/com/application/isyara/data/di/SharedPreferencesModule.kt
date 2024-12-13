package com.application.isyara.data.di

import com.application.isyara.utils.auth.ISessionManager
import com.application.isyara.data.local.SessionManager
import com.application.isyara.data.local.UserPreferences
import com.application.isyara.utils.auth.IUserPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class SharedPreferencesModule {

    @Binds
    @Singleton
    abstract fun bindSessionManager(
        sessionManager: SessionManager
    ): ISessionManager

    @Binds
    @Singleton
    abstract fun bindUserPreferences(
        userPreferences: UserPreferences
    ): IUserPreferences
}
