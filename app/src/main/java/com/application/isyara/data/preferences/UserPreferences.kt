package com.application.isyara.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.application.isyara.utils.auth.IUserPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    @ApplicationContext private val context: Context
) : IUserPreferences {

    private val Context.dataStore by preferencesDataStore(name = "user_prefs")

    companion object {
        private val LAST_REGISTRATION_ATTEMPT_KEY = longPreferencesKey("last_registration_attempt")
    }

    override fun getLastRegistrationAttempt(): Flow<Long?> {
        return context.dataStore.data.map { preferences ->
            preferences[LAST_REGISTRATION_ATTEMPT_KEY]
        }
    }

    override suspend fun setLastRegistrationAttempt(timeMillis: Long) {
        context.dataStore.edit { preferences ->
            preferences[LAST_REGISTRATION_ATTEMPT_KEY] = timeMillis
        }
    }

    override suspend fun clearLastRegistrationAttempt() {
        context.dataStore.edit { preferences ->
            preferences.remove(LAST_REGISTRATION_ATTEMPT_KEY)
        }
    }
}
