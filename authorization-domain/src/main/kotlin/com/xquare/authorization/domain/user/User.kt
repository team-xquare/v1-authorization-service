package com.xquare.authorization.domain.user

import java.time.LocalDate
import java.util.UUID

data class User(
    val name: String,
    val id: UUID,
    val birthDay: LocalDate,
    val grade: Int,
    val classNum: Int,
    val profileFileName: String?,
    val password: String,
    val accountId: String,
    val num: Int
)
