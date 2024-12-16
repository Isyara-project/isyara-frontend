package com.application.isyara.data.repository

import android.content.Context
import com.application.isyara.data.preferences.LanguagePreferences
import com.application.isyara.utils.state.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageRepository @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun getLanguage(): Result<String> {
        return try {
            val language = withContext(Dispatchers.IO) {
                LanguagePreferences.getLanguage(context)
            }
            Result.Success(language)
        } catch (e: Exception) {
            Result.Error(message = e.localizedMessage ?: "Unknown Error")
        }
    }
}
