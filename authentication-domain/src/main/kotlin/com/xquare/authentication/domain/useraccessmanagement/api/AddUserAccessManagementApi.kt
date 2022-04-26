package com.xquare.authentication.domain.useraccessmanagement.api

import java.util.UUID

interface AddUserAccessManagementApi {
    suspend fun addUserAccess(userId: UUID)
}
