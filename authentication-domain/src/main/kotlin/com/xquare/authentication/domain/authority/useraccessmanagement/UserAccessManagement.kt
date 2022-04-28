package com.xquare.authentication.domain.authority.useraccessmanagement

import com.xquare.authentication.domain.annotations.Aggregate
import java.util.UUID

@Aggregate
class UserAccessManagement(
    val id: UUID,
    val userId: UUID,
    val authorityId: UUID
)
