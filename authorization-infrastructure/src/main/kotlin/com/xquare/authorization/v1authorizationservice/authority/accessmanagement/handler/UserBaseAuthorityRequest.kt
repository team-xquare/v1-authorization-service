package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.handler

import java.util.UUID
import javax.validation.constraints.NotNull

class UserBaseAuthorityRequest(
    @field:NotNull
    val userId: UUID
)
