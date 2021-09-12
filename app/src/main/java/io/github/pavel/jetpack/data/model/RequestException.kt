package io.github.pavel.jetpack.data.model

class RequestException(val code: Int, message: String) : Throwable(message)