package com.rezapour.listofpeople.util

sealed class UiState<out T> {

    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: Int) : UiState<Nothing>()
    object DefaultError : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}