package com.molkos.testcompaniesapp.domain.model

sealed class Response<T> {
    data class Success<T>(val body: T) : Response<T>()
    data class Error<T>(val error: String) : Response<T>()
}
