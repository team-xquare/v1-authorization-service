package com.xquare.authorization.v1authorizationservice.authority

import com.xquare.authorization.v1authorizationservice.configuration.r2dbc.BaseR2DBCEntity
import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("tbl_authority")
class AuthorityEntity(
    @Id
    private var id: UUID = UUID(0, 0),
    val name: String,
    val description: String,
    val state: AuthorityState
) : BaseR2DBCEntity()
