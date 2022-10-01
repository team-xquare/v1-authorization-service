package com.xquare.authorization.domain.authority.useraccessmanagement.api

import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityListResponse
import java.util.UUID

interface AccessManagementService {
    suspend fun getUserAuthorityList(userId: UUID): AuthorityListResponse
    suspend fun saveBaseAccessManagement(userId: UUID)
    suspend fun deleteBaseAccessManagement(userId: UUID)
}
