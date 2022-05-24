package com.xquare.authorization.domain.authority.useraccessmanagement

import com.xquare.authorization.domain.annotations.Aggregate
import java.util.UUID

@Aggregate
class UserAccessManagement(
    val id: UUID,
    val userId: UUID,
    val authorityId: UUID
)
