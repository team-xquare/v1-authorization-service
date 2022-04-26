package com.xquare.authorization.v1authorizationservice.authority.accessmanagement

import com.xquare.authorization.v1authorizationservice.configuration.r2dbc.BaseR2DBCEntity
import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("tbl_access_management_user")
class UserAccessManagementEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    val userId: UUID,
    val authorityId: UUID,
    val state: UserAccessManagementState
) : BaseR2DBCEntity()
