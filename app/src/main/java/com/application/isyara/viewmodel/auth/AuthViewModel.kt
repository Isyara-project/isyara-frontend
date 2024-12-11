package com.application.isyara.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.*
import com.application.isyara.data.repository.AuthRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _registerState = MutableStateFlow<Result<RegisterResponse>>(Result.Idle)
    val registerState: StateFlow<Result<RegisterResponse>> = _registerState

    private val _otpState = MutableStateFlow<Result<OtpResponse>>(Result.Idle)
    val otpState: StateFlow<Result<OtpResponse>> = _otpState

    private val _resendOtpState = MutableStateFlow<Result<ResendOtpResponse>>(Result.Loading)
    val resendOtpState: StateFlow<Result<ResendOtpResponse>> get() = _resendOtpState

    private val _forgotPasswordState =
        MutableStateFlow<Result<ForgotPasswordResponse>>(Result.Loading)
    val forgotPasswordState: StateFlow<Result<ForgotPasswordResponse>> get() = _forgotPasswordState

    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Loading)
    val loginState: StateFlow<Result<LoginResponse>> get() = _loginState

    private val _resetPasswordState =
        MutableStateFlow<Result<ResetPasswordResponse>>(Result.Loading)
    val resetPasswordState: StateFlow<Result<ResetPasswordResponse>> get() = _resetPasswordState

    private val _feedbackState = MutableStateFlow<Result<FeedbackResponse>>(Result.Loading)
    val feedbackState: StateFlow<Result<FeedbackResponse>> get() = _feedbackState

    private val _feedbackHistoriesState =
        MutableStateFlow<Result<FeedbackHistoryResponse>>(Result.Loading)
    val feedbackHistoriesState: StateFlow<Result<FeedbackHistoryResponse>> get() = _feedbackHistoriesState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState


    // Fungsi untuk Register
    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            authRepository.registerUser(registerRequest).collect { result ->
                _registerState.value = result
            }
        }
    }

    // Fungsi untuk verifikasi OTP
    fun verifyOtp(otpRequest: OtpRequest, token: String) {
        viewModelScope.launch {
            authRepository.verifyOtp(token, otpRequest).collect { result ->
                _otpState.value = result
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

    fun getFeedbackHistories() {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                authRepository.getFeedbackHistories().collect { result ->
                    _feedbackHistoriesState.value = result
                }
            } catch (e: Exception) {
                _feedbackHistoriesState.value =
                    Result.Error("Fetching feedback histories failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex = Regex("^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#\$%^&+=]).{8,16}\$")
        return passwordRegex.matches(password)
    }
}
