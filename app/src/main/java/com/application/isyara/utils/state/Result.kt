package com.application.isyara.utils.state

sealed class Result<out T> {
    object Idle : Result<Nothing>()
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String, val errorCode: Int? = null) : Result<Nothing>()
}
