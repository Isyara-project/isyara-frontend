package com.application.isyara.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.*
import com.application.isyara.data.repository.AuthRepository
import com.application.isyara.utils.auth.ISessionManager
import com.application.isyara.utils.auth.IUserPreferences
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    internal val sessionManager: ISessionManager,
    private val userPreferences: IUserPreferences
) : ViewModel() {

    private val _registerState = MutableStateFlow<Result<RegisterResponse>>(Result.Idle)
    val registerState: StateFlow<Result<RegisterResponse>> = _registerState.asStateFlow()

    private val _otpState = MutableStateFlow<Result<OtpResponse>>(Result.Idle)
    val otpState: StateFlow<Result<OtpResponse>> = _otpState.asStateFlow()

    private val _resendOtpState = MutableStateFlow<Result<ResendOtpResponse>>(Result.Idle)
    val resendOtpState: StateFlow<Result<ResendOtpResponse>> = _resendOtpState.asStateFlow()

    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Idle)
    val loginState: StateFlow<Result<LoginResponse>> = _loginState.asStateFlow()

    private val _profileState = MutableStateFlow<Result<ProfileResponse>>(Result.Idle)
    val profileState: StateFlow<Result<ProfileResponse>> = _profileState.asStateFlow()

    val lastRegistrationAttempt: StateFlow<Long?> = userPreferences.getLastRegistrationAttempt()
        .stateIn(viewModelScope, SharingStarted.Lazily, null)

    /**
     * Fungsi untuk mendaftarkan pengguna baru.
     * @param registerRequest Data yang dibutuhkan untuk registrasi.
     */
    fun registerUser(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            authRepository.registerUser(registerRequest)
                .onStart { _registerState.value = Result.Loading }
                .catch { e ->
                    Timber.e(e, "Error during registerUser")
                    _registerState.value = Result.Error(e.message ?: "Registrasi gagal")
                }
                .collect { result ->
                    _registerState.value = result
                }
        }
    }

    /**
     * Fungsi untuk memverifikasi OTP yang dikirimkan.
     * @param token Token yang diperlukan untuk verifikasi OTP.
     * @param otpRequest Data OTP yang dikirimkan oleh pengguna.
     */
    fun verifyOtp(token: String, otpRequest: OtpRequest) {
        viewModelScope.launch {
            authRepository.verifyOtp(token, otpRequest)
                .onStart { _otpState.value = Result.Loading }
                .catch { e ->
                    Timber.e(e, "Error during verifyOtp")
                    _otpState.value = Result.Error(e.message ?: "Verifikasi OTP gagal")
                }
                .collect { result ->
                    _otpState.value = result
                }
        }
    }

    /**
     * Fungsi untuk meminta pengiriman ulang OTP.
     * @param token Token yang diperlukan untuk resend OTP.
     */
    fun resendOtp(token: String) {
        viewModelScope.launch {
            authRepository.resendOtp(token)
                .onStart { _resendOtpState.value = Result.Loading }
                .catch { e ->
                    Timber.e(e, "Error during resendOtp")
                    _resendOtpState.value = Result.Error(e.message ?: "Resend OTP gagal")
                }
                .collect { result ->
                    Timber.d("ViewModel: Resend OTP result: $result")
                    _resendOtpState.value = result
                }
        }
    }

    /**
     * Fungsi untuk login pengguna.
     * @param identifier Username atau email pengguna.
     * @param password Kata sandi pengguna.
     */
    fun login(identifier: String, password: String) {
        val loginRequest = LoginRequest(identifier, password)

        viewModelScope.launch {
            authRepository.loginUser(loginRequest)
                .onStart { _loginState.value = Result.Loading }
                .catch { e ->
                    Timber.e(e, "Error during login")
                    _loginState.value = Result.Error(e.message ?: "Login gagal")
                }
                .collect { result ->
                    _loginState.value = result
                }
        }
    }

    /**
     * Fungsi untuk memeriksa apakah pengguna sudah login berdasarkan token yang disimpan.
     */
    fun checkUserLoggedIn() {
        viewModelScope.launch {
            val token = sessionManager.getToken()
            if (!token.isNullOrEmpty()) {
                Timber.d("User is already logged in with token: $token")
                authRepository.getProfile(token)
                    .onStart { _profileState.value = Result.Loading }
                    .catch { e ->
                        Timber.e(e, "Error during checkUserLoggedIn")
                        _profileState.value = Result.Error(e.message ?: "Pengguna tidak terdaftar")
                    }
                    .collect { result ->
                        _profileState.value = result
                        when (result) {
                            is Result.Success -> {
                                _loginState.value = Result.Success(
                                    LoginResponse(
                                        message = "User is logged in",
                                        access_token = token
                                    )
                                )
                            }

                            is Result.Error -> {
                                _loginState.value = Result.Error(result.message)
                            }

                            else -> {
                            }
                        }
                    }
            } else {
                Timber.d("No token found, user is not logged in")
                _loginState.value = Result.Error("User not logged in")
            }
        }
    }

    /**
     * Fungsi untuk logout pengguna.
     */
    fun logout() {
        viewModelScope.launch {
            sessionManager.clearToken()
            userPreferences.clearLastRegistrationAttempt()
            _loginState.value = Result.Idle
            _profileState.value = Result.Idle
            Timber.d("User logged out")
        }
    }
}
