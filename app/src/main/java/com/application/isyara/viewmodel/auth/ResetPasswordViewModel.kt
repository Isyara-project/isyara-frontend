package com.application.isyara.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.ResetPasswordResponse
import com.application.isyara.data.repository.PasswordRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(private val repository: PasswordRepository) :
    ViewModel() {

    private val _resetPasswordState =
        MutableStateFlow<Result<ResetPasswordResponse>>(Result.Idle)
    val resetPasswordState: StateFlow<Result<ResetPasswordResponse>> = _resetPasswordState

    fun resetPassword(token: String, password: String) {
        viewModelScope.launch {
            _resetPasswordState.value = Result.Loading
            repository.resetPassword(token, password).collect { result ->
                _resetPasswordState.value = result
            }
        }
    }
}
