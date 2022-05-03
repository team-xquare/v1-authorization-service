package com.xquare.authentication.domain.authority.spi

import com.xquare.authentication.domain.authority.Authority

interface AuthorityRepositorySpi {
    suspend fun getBaseUserAuthorities(): List<Authority>
}
