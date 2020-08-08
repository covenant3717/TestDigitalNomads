package com.evgeny.testdigitalnomads.repository


sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Error(val errorMessage: String? = "") : Resource<Nothing>()
    data class Progress(val isLoading: Boolean = false) : Resource<Nothing>()
}