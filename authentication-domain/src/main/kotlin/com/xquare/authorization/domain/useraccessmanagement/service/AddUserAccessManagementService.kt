package com.xquare.authorization.domain.useraccessmanagement.service

import com.xquare.authorization.domain.useraccessmanagement.api.AddUserAccessManagementApi
import com.xquare.authorization.domain.useraccessmanagement.spi.UserAccessManagementRepositorySpi
import com.xquare.v1userservice.annotations.SagaStep
import java.util.UUID

@SagaStep
class AddUserAccessManagementService(
    private val userAccessManagementRepositorySpi: UserAccessManagementRepositorySpi
) : AddUserAccessManagementApi{
    override suspend fun addUserAccess(userId: UUID) {
//        userAccessManagementRepositorySpi.saveUserAccessManagementAndOutbox()
    }
}
