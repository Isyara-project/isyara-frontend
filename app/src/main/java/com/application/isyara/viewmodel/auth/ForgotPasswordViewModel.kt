package com.application.isyara.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.ForgotPasswordResponse
import com.application.isyara.data.repository.PasswordRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(private val repository: PasswordRepository) :
    ViewModel() {

    private val _forgotPasswordState =
        MutableStateFlow<Result<ForgotPasswordResponse>>(Result.Idle)
    val forgotPasswordState: StateFlow<Result<ForgotPasswordResponse>> = _forgotPasswordState

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            _forgotPasswordState.value = Result.Loading
            repository.forgotPassword(email).collect { result ->
                _forgotPasswordState.value = result
            }
        }
    }
}
