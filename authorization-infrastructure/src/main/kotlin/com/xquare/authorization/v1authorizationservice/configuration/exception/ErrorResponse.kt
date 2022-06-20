package com.xquare.authorization.v1authorizationservice.configuration.exception

data class ErrorResponse(
    val errorMessage: String?,
    val statusCode: Int
)
