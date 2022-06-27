package com.xquare.authorization.domain.authority.useraccessmanagement.api

import java.util.UUID

interface SaveUserBaseAuthorityApi {
    suspend fun saveBaseAuthority(userId: UUID)
}
