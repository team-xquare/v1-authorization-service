package com.xquare.authorization.domain.authority.spi

import com.xquare.authorization.domain.authority.Authority
import com.xquare.authorization.domain.authority.useraccessmanagement.UserAccessManagement
import java.util.*

interface AuthorityRepositorySpi {
    suspend fun getBaseUserAuthorities(): List<Authority>
    suspend fun getUserAuthorities(authorities: List<String>): List<Authority>
    suspend fun getUserAuthority(userId: UUID): List<Authority>
    suspend fun getUserAuthorityByType(userId: UUID, type: String): List<Authority>
    suspend fun saveAllUserAccessManagement(userAccessManagements: List<UserAccessManagement>): List<UserAccessManagement>
    suspend fun deleteAllUserAccessManagement(userId: UUID, userAccessManagements: List<UUID>)
}
