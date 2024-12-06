package com.application.isyara.viewmodel.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.local.SessionManager
import com.application.isyara.data.model.LoginRequest
import com.application.isyara.data.model.LoginResponse
import com.application.isyara.data.repository.AuthRepository
import com.application.isyara.utils.auth.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    val sessionManager: SessionManager
) : ViewModel() {

    private val _loginState = MutableStateFlow<Result<LoginResponse>>(Result.Loading)
    val loginState: StateFlow<Result<LoginResponse>> get() = _loginState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> get() = _errorState

    fun login(identifier: String, password: String) {
        val loginRequest = LoginRequest(identifier, password)

        viewModelScope.launch {
            _loadingState.value = true

            authRepository.loginUser(loginRequest).collect { result ->
                _loadingState.value = false

                when (result) {
                    is Result.Loading -> {
                        _loginState.value = Result.Loading
                    }

                    is Result.Success -> {
                        val token = result.data.access_token
                        if (token.isNotEmpty()) {
                            sessionManager.saveToken(token)
                            _loginState.value = Result.Success(result.data)
                            Timber.tag("LoginViewModel").d("Login success: %s", result.data)
                        } else {
                            _loginState.value = Result.Error("Login failed: Token is null or empty")
                        }
                    }

                    is Result.Error -> {
                        _loginState.value = Result.Error(result.message)
                        _errorState.value = result.message
                        Timber.e("Login failed: ${result.message}")
                    }
                }
            }
        }
    }

    fun checkUserLoggedIn() {
        viewModelScope.launch {
            val token = sessionManager.getToken()
            if (!token.isNullOrEmpty()) {
                Timber.d("User is already logged in with token: $token")
                _loginState.value = Result.Success(LoginResponse("success", token))
            } else {
                Timber.d("No token found, user is not logged in")
                _loginState.value = Result.Error("User not logged in")
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            sessionManager.clearToken()
            _loginState.value = Result.Error("Logged out successfully")
            Timber.d("User logged out")
        }
    }
}
