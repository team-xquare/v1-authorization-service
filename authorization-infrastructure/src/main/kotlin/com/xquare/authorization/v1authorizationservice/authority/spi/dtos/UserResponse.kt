package com.xquare.authorization.v1authorizationservice.authority.spi.dtos

import java.time.LocalDate
import java.util.*

data class UserResponse(
    val name: String,
    val id: UUID,
    val birthDay: LocalDate,
    val grade: Int,
    val classNum: Int,
    val profileFileName: String,
    val password: String,
    val accountId: String,
    val num: Int
)
