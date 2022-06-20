package com.xquare.authorization.v1authorizationservice.configuration.validate

import com.xquare.authorization.domain.exception.BaseException

class BadRequestException(
    errorMessage: String
) : BaseException(errorMessage, 400)
