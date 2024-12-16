package com.application.isyara.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import java.util.Locale

object LanguagePreferences {

    private val Context.dataStore by preferencesDataStore(name = "locale_prefs")
    private val SELECTED_LANGUAGE = stringPreferencesKey("Locale.Helper.Selected.Language")

    suspend fun getLanguage(context: Context): String {
        val preferences = context.dataStore.data.first()
        return preferences[SELECTED_LANGUAGE] ?: Locale.getDefault().language
    }
}
