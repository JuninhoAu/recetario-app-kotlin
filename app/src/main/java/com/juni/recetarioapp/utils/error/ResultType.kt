package com.juni.recetarioapp.utils.error

sealed class ResultType<out Success, out Error> {
    data class Success<out Success>(val data: Success) : ResultType<Success, Nothing>()
    data class Error<out Error>(val error: Error) : ResultType<Nothing, Error>()
}