package com.xquare.authorization.v1authorizationservice.authority.exceptions

import com.xquare.authorization.domain.exception.BaseException

class UserRequestFailedException(
    message: String,
    statusCode: Int
) : BaseException(message, statusCode)
