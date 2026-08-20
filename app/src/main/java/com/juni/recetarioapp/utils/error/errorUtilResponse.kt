package com.juni.recetarioapp.utils.error

sealed class Failure(val message: String) {
    class NetworkFailure(message: String) : Failure(message)
    class ApiFailure(message: String) : Failure(message)
    class UnknownFailure(message: String) : Failure(message)
}

object errorUtilResponse {
    fun errorHandler(t: Throwable): Failure {
        return when (t) {
            is java.net.SocketTimeoutException -> Failure.NetworkFailure("Timeout de red")
            is java.io.IOException -> Failure.NetworkFailure("Error de conexión")
            is retrofit2.HttpException -> Failure.ApiFailure("Error en API: ${t.code()}")
            else -> Failure.UnknownFailure("Error desconocido: ${t.localizedMessage}")
        }
    }
}