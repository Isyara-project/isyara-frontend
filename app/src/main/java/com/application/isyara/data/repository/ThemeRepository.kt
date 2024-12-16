package com.application.isyara.repository

import android.content.Context
import com.application.isyara.utils.ThemePreferences
import com.application.isyara.utils.settings.AppTheme
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemeRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun getTheme(): AppTheme {
        val theme = ThemePreferences.getTheme(context)
        return when (theme) {
            AppTheme.LIGHT.name -> AppTheme.LIGHT
            AppTheme.DARK.name -> AppTheme.DARK
            else -> AppTheme.SYSTEM_DEFAULT
        }
    }

    fun setTheme(theme: AppTheme) {
        val themeString = theme.name
        ThemePreferences.setTheme(context, themeString)
    }
}