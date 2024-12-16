package com.application.isyara.viewmodel.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.ChangePasswordResponse
import com.application.isyara.data.preferences.SessionManager
import com.application.isyara.data.repository.ChangePasswordRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val repository: ChangePasswordRepository,
    internal val sessionManager: SessionManager
) :
    ViewModel() {

    private val _changePasswordState =
        MutableStateFlow<Result<ChangePasswordResponse>>(Result.Loading)
    val changePasswordState: StateFlow<Result<ChangePasswordResponse>> get() = _changePasswordState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    fun changePassword(oldPass: String, newPass: String) {
        viewModelScope.launch {
            _loadingState.value = true
            try {
                repository.changePassword(oldPass, newPass).collect { result ->
                    when (result) {
                        is Result.Success -> {
                            _changePasswordState.value = Result.Success(result.data)
                        }

                        is Result.Error -> {
                            _changePasswordState.value = Result.Error(result.message)
                        }

                        else -> {
                            _changePasswordState.value = Result.Loading
                        }
                    }
                }
            } catch (e: Exception) {
                _changePasswordState.value = Result.Error("Change password failed: ${e.message}")
            } finally {
                _loadingState.value = false
            }
        }
    }


}
