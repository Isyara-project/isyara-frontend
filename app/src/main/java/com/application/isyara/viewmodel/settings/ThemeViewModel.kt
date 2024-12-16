package com.application.isyara.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.repository.ThemeRepository
import com.application.isyara.utils.settings.AppTheme
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val themeRepository: ThemeRepository
) : ViewModel() {

    private val _themeState = MutableStateFlow<Result<AppTheme>>(Result.Idle)
    val themeState: StateFlow<Result<AppTheme>> = _themeState

    init {
        loadTheme()
    }

    private fun loadTheme() {
        viewModelScope.launch {
            _themeState.value = Result.Loading
            try {
                val currentTheme = themeRepository.getTheme()
                _themeState.value = Result.Success(currentTheme)
                Timber.d("Loaded Theme: $currentTheme")
            } catch (e: Exception) {
                _themeState.value = Result.Error(e.message ?: "Unknown Error")
                Timber.e("Error loading theme: " + e.message)
            }
        }
    }

    fun updateTheme(theme: AppTheme) {
        viewModelScope.launch {
            _themeState.value = Result.Loading
            try {
                themeRepository.setTheme(theme)
                _themeState.value = Result.Success(theme)
                Timber.d("Theme updated to: $theme")
            } catch (e: Exception) {
                _themeState.value = Result.Error(e.message ?: "Failed to update theme")
                Timber.e("Error updating theme: " + e.message)
            }
        }
    }
}