package com.xquare.authorization.v1authorizationservice.authority

import com.xquare.authorization.domain.authority.AuthorityState
import com.xquare.authorization.v1authorizationservice.configuration.r2dbc.BaseR2DBCEntity
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
