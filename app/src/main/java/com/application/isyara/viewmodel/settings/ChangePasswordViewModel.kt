package com.application.isyara.viewmodel.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.application.isyara.data.model.ChangePasswordResponse
import com.application.isyara.data.repository.ChangePasswordRepository
import com.application.isyara.utils.state.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(private val repository: ChangePasswordRepository) :
    ViewModel() {

    private val _changePasswordState = MutableStateFlow<Result<ChangePasswordResponse>>(Result.Idle)
    val changePasswordState: StateFlow<Result<ChangePasswordResponse>> = _changePasswordState

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    fun changePassword(oldPassword: String, newPassword: String) {
        viewModelScope.launch {
            _changePasswordState.value = Result.Loading
            repository.changePassword(oldPassword, newPassword).collect { result ->
                _changePasswordState.value = result
            }
        }
    }
}
