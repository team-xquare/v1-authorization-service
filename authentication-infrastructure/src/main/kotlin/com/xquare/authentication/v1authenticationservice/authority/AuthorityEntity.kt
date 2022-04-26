package com.xquare.authentication.v1authenticationservice.authority

import com.xquare.authentication.v1authenticationservice.configuration.r2dbc.BaseR2DBCEntity
import java.util.UUID
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("tbl_authority")
class AuthorityEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String
) : BaseR2DBCEntity() {
    var state: AuthorityState = AuthorityState.CREATE_PENDING
        private set
}
