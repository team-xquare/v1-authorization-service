package com.xquare.authorization.v1authorizationservice.configuration.exception

import com.xquare.authorization.domain.exception.BaseException

class InternalServerError(
    errorMessage: String?
) : BaseException(errorMessage, 500)
