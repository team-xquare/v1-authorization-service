package com.xquare.authorization.domain.authority.useraccessmanagement.api

import java.util.UUID

interface AddUserBaseAuthorityStep {
    suspend fun processStep(userId: UUID)
}
