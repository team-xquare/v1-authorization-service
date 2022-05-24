package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.spi

import com.fasterxml.jackson.databind.ObjectMapper
import com.xquare.authorization.domain.authority.useraccessmanagement.UserAccessManagement
import com.xquare.authorization.domain.authority.useraccessmanagement.spi.UserAccessManagementRepositorySpi
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.UserAccessManagementEntity
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.mapper.UserAccessManagementDomainMapper
import com.xquare.authorization.v1authorizationservice.outbox.OutboxEntity
import java.time.LocalDateTime
import java.util.UUID
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class UserAccessManagementRepositorySpiImpl(
    private val userAccessManagementDomainMapper: UserAccessManagementDomainMapper,
    private val entityOperations: R2dbcEntityOperations,
    private val objectMapper: ObjectMapper
) : UserAccessManagementRepositorySpi {
    @Transactional
    override suspend fun saveAllUserAccessManagementAndOutbox(userAccessManagements: List<UserAccessManagement>): List<UserAccessManagement> {
        val userAccessManagementEntityListToSave = userAccessManagements
            .map { userAccessManagementDomainMapper.userAccessManagementDomainToEntity(it) }

        val savedUserAccessManagements = saveAllUserAccessManagement(userAccessManagementEntityListToSave)

        val outboxEntity = userAccessManagements.firstOrNull()?.toOutboxEntity()
        outboxEntity?.save()

        return savedUserAccessManagements
            .map { userAccessManagementDomainMapper.userAccessManagementEntityToDomain(it) }
    }

    private suspend fun saveAllUserAccessManagement(userAccessManagements: List<UserAccessManagementEntity>) =
        userAccessManagements.map { entityOperations.insert(it).awaitSingle() }

    private fun UserAccessManagement.toOutboxEntity() =
        OutboxEntity(
            id = UUID.randomUUID(),
            type = "AuthorityCreated",
            aggregateId = this.id,
            timestamp = LocalDateTime.now(),
            payload = this.buildOutboxEntityJson().toString(),
            aggregateType = "authority"
        )

    private fun UserAccessManagement.buildOutboxEntityJson() =
        objectMapper.createObjectNode().apply {
            put("user_id", this@buildOutboxEntityJson.userId.toString())
        }

    private suspend fun OutboxEntity.save() =
        entityOperations.insert(this).awaitSingle()
}
