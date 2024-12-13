package com.application.isyara.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.ForgotPasswordResponse
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
class ForgotPasswordViewModel @Inject constructor(
    private val repository: PasswordRepository
) : ViewModel() {

    private val _forgotPasswordState = MutableStateFlow<Result<ForgotPasswordResponse>>(Result.Idle)
    val forgotPasswordState: StateFlow<Result<ForgotPasswordResponse>> =
        _forgotPasswordState.asStateFlow()

    private val _emailError = MutableStateFlow<String?>(null)
    val emailError: StateFlow<String?> = _emailError.asStateFlow()

    fun forgotPassword(email: String) {
        viewModelScope.launch {
            Timber.d("Initiating forgot password with email: $email")
            repository.forgotPassword(email)
                .catch { e ->
                    Timber.e("Unexpected error during forgot password: ${e.localizedMessage}")
                    _forgotPasswordState.value =
                        Result.Error("Unexpected error: ${e.localizedMessage}")
                }
                .collect { result ->
                    Timber.d("Received result: $result")
                    _forgotPasswordState.value = result
                }
        }
    }

    /**
     * Fungsi untuk memproses hasil forgot password dan menetapkan error email
     */
    fun processForgotPasswordResult() {
        viewModelScope.launch {
            when (val result = _forgotPasswordState.value) {
                is Result.Success -> {
                    _emailError.value = null
                }

                is Result.Error -> {
                    when (result.errorCode) {
                        500 -> _emailError.value = "Email belum terdaftar"
                        400 -> _emailError.value = "Permintaan tidak valid"
                        else -> _emailError.value = result.message
                    }
                }

                else -> {
                    _emailError.value = null
                }
            }
        }
    }

    fun resetState() {
        _forgotPasswordState.value = Result.Idle
        _emailError.value = null
    }
}
