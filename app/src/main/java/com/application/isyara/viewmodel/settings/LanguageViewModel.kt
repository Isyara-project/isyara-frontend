package com.application.isyara.ui.main.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.repository.LanguageRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val repository: LanguageRepository
) : ViewModel() {

    private val _languageState = MutableStateFlow<Result<String>>(Result.Idle)
    val languageState: StateFlow<Result<String>> = _languageState

    private val _setLanguageState = MutableStateFlow<Result<Unit>>(Result.Idle)
    val setLanguageState: StateFlow<Result<Unit>> = _setLanguageState

    init {
        fetchLanguage()
    }

    private fun fetchLanguage() {
        viewModelScope.launch {
            _languageState.value = Result.Loading
            val result = repository.getLanguage()
            _languageState.value = result
        }
    }
}
