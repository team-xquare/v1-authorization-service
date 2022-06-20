package com.xquare.authorization.domain.exception

abstract class BaseException(
    val errorMessage: String?,
    val statusCode: Int
) : RuntimeException(errorMessage) {
    override fun fillInStackTrace(): Throwable {
        return this
    }
}
