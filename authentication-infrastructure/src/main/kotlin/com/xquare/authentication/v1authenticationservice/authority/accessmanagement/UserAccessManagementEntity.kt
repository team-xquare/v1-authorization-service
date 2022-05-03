package com.xquare.authentication.v1authenticationservice.authority.accessmanagement

import com.xquare.authentication.v1authenticationservice.configuration.r2dbc.BaseR2DBCEntity
import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("tbl_access_management_user")
class UserAccessManagementEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    val userId: UUID,
    val authorityId: UUID
) : BaseR2DBCEntity()
