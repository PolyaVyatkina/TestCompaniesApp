package com.molkos.testcompaniesapp.model

sealed class UiState {
    data class Success(val data: Any) : UiState()
    data class Error(val error: String) : UiState()
    object Loading : UiState()
}
