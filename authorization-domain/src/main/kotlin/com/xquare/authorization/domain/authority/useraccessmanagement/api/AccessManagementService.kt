package com.xquare.authorization.domain.authority.useraccessmanagement.api

import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityListByTypeResponse
import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityListResponse
import java.util.UUID

interface AccessManagementService {
    suspend fun getUserAuthorityList(userId: UUID): AuthorityListResponse
    suspend fun getUserAuthorityListByType(userId: UUID, type: String): AuthorityListByTypeResponse
    suspend fun saveBaseAccessManagement(userId: UUID)
    suspend fun deleteBaseAccessManagement(userId: UUID)
}
