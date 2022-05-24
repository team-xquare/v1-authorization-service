package com.xquare.authorization.domain.authority.spi

import com.xquare.authorization.domain.authority.Authority

interface AuthorityRepositorySpi {
    suspend fun getBaseUserAuthorities(): List<Authority>
}
