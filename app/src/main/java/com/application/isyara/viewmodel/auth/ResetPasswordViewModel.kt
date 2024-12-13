package com.application.isyara.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.ResetPasswordResponse
import com.application.isyara.data.repository.PasswordRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val repository: PasswordRepository
) : ViewModel() {

    private val _resetPasswordState = MutableStateFlow<Result<ResetPasswordResponse>>(Result.Idle)
    val resetPasswordState: StateFlow<Result<ResetPasswordResponse>> =
        _resetPasswordState.asStateFlow()

    fun resetPassword(token: String, password: String) {
        viewModelScope.launch {
            Timber.d("Initiating reset password with token: $token and password: $password")
            repository.resetPassword(token, password)
                .catch { e ->
                    Timber.e("Unexpected error during password reset: ${e.localizedMessage}")
                    _resetPasswordState.value =
                        Result.Error("Unexpected error: ${e.localizedMessage}")
                }
                .collect { result ->
                    when (result) {
                        is Result.Loading -> {
                            Timber.d("Loading state for password reset")
                        }

                        is Result.Success -> {
                            Timber.d("Password reset success")
                        }

                        is Result.Error -> {
                            Timber.e("Password reset error: ${result.message}")
                        }

                        is Result.Idle -> {
                            Timber.d("Idle state for password reset")
                        }
                    }
                    _resetPasswordState.value = result
                }
        }
    }

    // Optional: Reset state to Idle
    fun resetState() {
        _resetPasswordState.value = Result.Idle
    }
}
