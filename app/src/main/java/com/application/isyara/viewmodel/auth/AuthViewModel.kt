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

    // State untuk berbagai operasi
    private val _registerState = MutableStateFlow<Result<RegisterResponse>>(Result.Loading)
    val registerState: StateFlow<Result<RegisterResponse>> get() = _registerState

    private val _otpState = MutableStateFlow<Result<OtpResponse>>(Result.Loading)
    val otpState: StateFlow<Result<OtpResponse>> get() = _otpState

    private val _resendOtpState = MutableStateFlow<Result<ResendOtpResponse>>(Result.Loading)
    val resendOtpState: StateFlow<Result<ResendOtpResponse>> get() = _resendOtpState

    private val _forgotPasswordState = MutableStateFlow<Result<ForgotPasswordResponse>>(Result.Loading)
    val forgotPasswordState: StateFlow<Result<ForgotPasswordResponse>> get() = _forgotPasswordState

    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Loading)
    val loginState: StateFlow<Result<LoginResponse>> get() = _loginState

    private val _resetPasswordState = MutableStateFlow<Result<ResetPasswordResponse>>(Result.Loading)
    val resetPasswordState: StateFlow<Result<ResetPasswordResponse>> get() = _resetPasswordState

    private val _feedbackState = MutableStateFlow<Result<FeedbackResponse>>(Result.Loading)
    val feedbackState: StateFlow<Result<FeedbackResponse>> get() = _feedbackState

Feedback
    private val _feedbackHistoriesState = MutableStateFlow<Result<FeedbackHistoryResponse>>(Result.Loading)
    val feedbackHistoriesState: StateFlow<Result<FeedbackHistoryResponse>> get() = _feedbackHistoriesState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    // Fungsi untuk Login
    fun loginUser(loginRequest: LoginRequest) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                authRepository.loginUser(loginRequest).collect { result ->
                    _loginState.value = result
                }
            } catch (e: Exception) {
                _loginState.value = Result.Error("Login failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Fungsi untuk Register
=======
    // Fungsi Register
  application
    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            try {
                _loadingState.value = true
                authRepository.registerUser(registerRequest).collect { result ->
                    _registerState.value = result
                }
            } catch (e: Exception) {
                _registerState.value = Result.Error("Registration failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Fungsi untuk Verifikasi OTP
    fun verifyOtp(otpRequest: OtpRequest, token: String) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                authRepository.verifyOtp(otpRequest, token).collect { result ->
                    _otpState.value = result
                }
            } catch (e: Exception) {
                _otpState.value = Result.Error("OTP verification failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Fungsi untuk Kirim Ulang OTP
    fun resendOtp(token: String) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                authRepository.resendOtp(token).collect { result ->
                    _resendOtpState.value = result
                }
            } catch (e: Exception) {
                _resendOtpState.value = Result.Error("Resend OTP failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Fungsi untuk Lupa Kata Sandi
    fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                authRepository.forgotPassword(forgotPasswordRequest).collect { result ->
                    _forgotPasswordState.value = result
                }
            } catch (e: Exception) {
                _forgotPasswordState.value = Result.Error("Forgot password failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Fungsi untuk Reset Password
    fun resetPassword(resetPasswordRequest: ResetPasswordRequest, token: String) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                if (!isPasswordValid(resetPasswordRequest.password)) {
                    _resetPasswordState.value =
                        Result.Error("Password must be 8-16 characters long, include one uppercase letter, one number, and one special character.")
                    return@launch
                }
                authRepository.resetPassword(resetPasswordRequest, token)
                    .collect { result ->
                        _resetPasswordState.value = result
                    }
            } catch (e: Exception) {
                _resetPasswordState.value = Result.Error("Reset password failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Fungsi untuk Mengirimkan Feedback
    fun sendFeedback(feedbackRequest: FeedbackRequest) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                authRepository.sendFeedback(feedbackRequest).collect { result ->
                    _feedbackState.value = result
                }
            } catch (e: Exception) {
                _feedbackState.value = Result.Error("Sending feedback failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Fungsi untuk Mendapatkan Riwayat Feedback
    fun getFeedbackHistories() {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                authRepository.getFeedbackHistories().collect { result ->
                    _feedbackHistoriesState.value = result
                }
            } catch (e: Exception) {
                _feedbackHistoriesState.value = Result.Error("Fetching feedback histories failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

    // Fungsi Validasi Password
    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#\$%^&+=]).{8,16}\$")
        return passwordRegex.matches(password)
    }
}
