package com.application.isyara.utils.auth

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

suspend fun saveCredentials(context: Context, email: String, password: String) {
    val saveEmailKey = stringPreferencesKey("email")
    val savePasswordKey = stringPreferencesKey("password")

    context.dataStore.edit { preferences ->
        preferences[saveEmailKey] = email
        preferences[savePasswordKey] = password
    }
}

suspend fun loadCredentials(context: Context): Pair<String?, String?> {
    val loadEmailKey = stringPreferencesKey("email")
    val loadPasswordKey = stringPreferencesKey("password")

    val preferences = context.dataStore.data.first()
    val email = preferences[loadEmailKey]
    val password = preferences[loadPasswordKey]
    return Pair(email, password)
}

suspend fun clearCredentials(context: Context) {
    context.dataStore.edit { preferences ->
        preferences.clear()
    }
}
