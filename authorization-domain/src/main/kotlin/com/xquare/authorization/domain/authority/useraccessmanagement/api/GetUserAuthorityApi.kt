package com.xquare.authorization.domain.authority.useraccessmanagement.api

import com.xquare.authorization.domain.authority.useraccessmanagement.api.dtos.AuthorityListResponse
import java.util.UUID

interface GetUserAuthorityApi {
    suspend fun getUserAuthorityList(userId: UUID): AuthorityListResponse
}
