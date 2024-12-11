package com.application.isyara.data.di

import com.application.isyara.BuildConfig
import com.application.isyara.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val DICTIONARY_BASE_URL = BuildConfig.DICTIONARY_URL
    private const val MAIN_API_BASE_URL = BuildConfig.BASE_URL

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    @RetrofitDictionary
    fun provideOkHttpClientDictionary(): OkHttpClient {
        val loggingInterceptor = provideLoggingInterceptor()

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @RetrofitMain
    fun provideOkHttpClientMainAPI(): OkHttpClient {
        val loggingInterceptor = provideLoggingInterceptor()

        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @RetrofitDictionary
    fun provideRetrofitDictionary(@RetrofitDictionary okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DICTIONARY_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @RetrofitMain
    fun provideRetrofitMainAPI(@RetrofitMain okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(MAIN_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @RetrofitDictionary
    fun provideApiServiceDictionary(@RetrofitDictionary retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @RetrofitMain
    fun provideApiServiceMainAPI(@RetrofitMain retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}
