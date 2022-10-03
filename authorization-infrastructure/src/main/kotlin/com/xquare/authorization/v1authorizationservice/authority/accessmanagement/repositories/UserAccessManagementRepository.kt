package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.repositories

import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.UserAccessManagementEntity
import java.util.UUID
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAccessManagementRepository : CoroutineCrudRepository<UserAccessManagementEntity, UUID> {
    suspend fun deleteAllByUserIdAndAuthorityIdIn(userId: UUID, authorityIds: List<UUID>)
}
