package com.xquare.authorization.domain.useraccessmanagement

import com.xquare.v1userservice.annotations.Aggregate
import java.util.UUID

@Aggregate
class UserAccessManagement(
    val id: UUID,
    val userId: UUID,
    val authorityId: UUID
)
