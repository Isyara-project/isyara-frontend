package com.application.isyara.utils

sealed class UIState {
    object Idle : UIState()
    object Loading : UIState()
    data class Success<T>(val data: T) : UIState()
    data class Error(val message: String) : UIState()
}
