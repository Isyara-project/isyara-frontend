package com.application.isyara.utils.dictionary

sealed class AlphabetState {
    object Loading : AlphabetState()
    data class Success(val pictures: List<String>) : AlphabetState()
    data class Error(val message: String) : AlphabetState()
}