package com.xquare.authorization.domain.authority.useraccessmanagement.spi

import com.xquare.authorization.domain.authority.Authority
import java.util.UUID

interface GetUserAuthoritySpi {
    suspend fun getUserAuthority(userId: UUID): List<Authority>
}
