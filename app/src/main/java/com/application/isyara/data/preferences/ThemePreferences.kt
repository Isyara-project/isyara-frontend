package com.application.isyara.utils

import android.content.Context
import android.content.SharedPreferences
import com.application.isyara.utils.settings.AppTheme

object ThemePreferences {
    private const val PREFS_NAME = "theme_prefs"
    private const val KEY_THEME = "selected_theme"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun getTheme(context: Context): String {
        return getPreferences(context).getString(KEY_THEME, AppTheme.SYSTEM_DEFAULT.name)
            ?: AppTheme.SYSTEM_DEFAULT.name
    }

    fun setTheme(context: Context, theme: String) {
        getPreferences(context).edit().putString(KEY_THEME, theme).apply()
    }
}
