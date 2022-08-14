package com.xquare.authorization.v1authorizationservice.configuration.exception

import com.xquare.authorization.domain.exception.BaseException

class RequestHandlerNotFoundException(
    errorMessage: String?
) : BaseException(errorMessage, 404)
