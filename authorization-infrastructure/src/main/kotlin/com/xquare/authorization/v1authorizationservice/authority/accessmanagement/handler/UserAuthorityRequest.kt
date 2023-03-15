package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.handler

import java.util.*
import javax.validation.constraints.NotNull

class UserAuthorityRequest(
    @field:NotNull
    val userId: UUID,

    @field:NotNull
    val authorities: MutableList<String>
)
