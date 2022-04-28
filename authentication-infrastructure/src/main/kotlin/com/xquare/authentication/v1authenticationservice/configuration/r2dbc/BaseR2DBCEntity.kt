package com.xquare.authentication.v1authenticationservice.configuration.r2dbc

import org.springframework.data.annotation.Version

abstract class BaseR2DBCEntity {
    @Version
    private var version: Int = 0
}