package com.application.isyara.data.preferences

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.application.isyara.utils.auth.ISessionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext context: Context
) : ISessionManager {

    companion object {
        private const val PREF_NAME = "com.application.isyara.PREFS"
        private const val KEY_TOKEN = "access_token"
    }

    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        PREF_NAME,
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    private val _tokenFlow = MutableStateFlow(sharedPreferences.getString(KEY_TOKEN, null))
    override val tokenFlow: StateFlow<String?> = _tokenFlow.asStateFlow()

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
        _tokenFlow.value = token
        Timber.d("Token saved: $token")
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null).also { token ->
            _tokenFlow.value = token
        }
    }

    override fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
        _tokenFlow.value = null
        Timber.d("Token cleared")
    }
}
