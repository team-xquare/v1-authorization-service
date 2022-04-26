package com.xquare.authentication.domain.useraccessmanagement.spi

import com.xquare.authentication.domain.useraccessmanagement.UserAccessManagement

interface UserAccessManagementRepositorySpi {
    suspend fun saveUserAccessManagementAndOutbox(userAccessManagement: UserAccessManagement): UserAccessManagement
}
