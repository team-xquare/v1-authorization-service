package com.xquare.authorization.v1authorizationservice.configuration.r2dbc

import org.springframework.data.annotation.Version

abstract class BaseR2DBCEntity {
    @Version
    protected var version: Int = 0
}
