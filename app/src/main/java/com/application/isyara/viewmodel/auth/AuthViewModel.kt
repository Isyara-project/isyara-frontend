package com.application.isyara.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.*
import com.application.isyara.data.repository.AuthRepository
import com.application.isyara.utils.auth.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _registerState = MutableStateFlow<Result<RegisterResponse>>(Result.Loading)
    val registerState: StateFlow<Result<RegisterResponse>> get() = _registerState

    private val _otpState = MutableStateFlow<Result<OtpResponse>>(Result.Loading)
    val otpState: StateFlow<Result<OtpResponse>> get() = _otpState

    private val _resendOtpState = MutableStateFlow<Result<ResendOtpResponse>>(Result.Loading)
    val resendOtpState: StateFlow<Result<ResendOtpResponse>> get() = _resendOtpState

    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Loading)
    val loginState: StateFlow<Result<LoginResponse>> get() = _loginState

    private val _forgotPasswordState = MutableStateFlow<Result<ForgotPasswordResponse>>(Result.Loading)
    val forgotPasswordState: StateFlow<Result<ForgotPasswordResponse>> get() = _forgotPasswordState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    // Fungsi Register
    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            try {
                _loadingState.value = true
                authRepository.registerUser(registerRequest).collect { result ->
                    _registerState.value = result
                    _loadingState.value = false
                }
            } catch (e: Exception) {
                _registerState.value = Result.Error("Registration failed: ${e.message}")
                _loadingState.value = false
            }
        }
    }

    // Fungsi Verifikasi OTP
    fun verifyOtp(otpRequest: OtpRequest, token: String) {
        viewModelScope.launch {
            authRepository.verifyOtp(otpRequest, token).collect { result ->
                _otpState.value = result
            }
        }
    }

    // Fungsi untuk login
    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _loadingState.value = true
            authRepository.loginUser(loginRequest).collect { result ->
                _loginState.value = result
                _loadingState.value = false
            }
        }
    }

    // Fungsi Kirim Ulang OTP
    fun resendOtp(token: String) {
        viewModelScope.launch {
            authRepository.resendOtp(token).collect { result ->
                _resendOtpState.value = result
            }
        }
    }

    // Fungsi Lupa Kata Sandi
    fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                authRepository.forgotPassword(forgotPasswordRequest).collect { result ->
                    _forgotPasswordState.value = result
                    _loadingState.value = false
                }
            } catch (e: Exception) {
                _forgotPasswordState.value = Result.Error("Forgot password failed: ${e.message}")
                _loadingState.value = false
            }
        }
    }
}
