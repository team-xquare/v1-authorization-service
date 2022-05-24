package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.xquare.authorization.domain.authority.useraccessmanagement.api.AddUserBaseAuthorityStep
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.events.AuthorityCreateFailEvent
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.events.UserCreatedEvent
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.router.AccessManagementEventRouter
import com.xquare.authorization.v1authorizationservice.configuration.debezium.DebeziumMessage
import com.xquare.authorization.v1authorizationservice.configuration.utils.UUIDUtils.toUUID
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class UserCreateEventHandler(
    private val addUserBaseAuthorityStep: AddUserBaseAuthorityStep,
    private val objectMapper: ObjectMapper,
    private val kafkaTemplate: KafkaTemplate<String, AuthorityCreateFailEvent>
) {
    @Transactional
    suspend fun createUserBaseAuthority(debeziumMessage: DebeziumMessage) {
        val userCreatedEvent = debeziumMessage.toUserCreatedEvent()
        try {
            addUserBaseAuthorityStep.processStep(userCreatedEvent.userId.toUUID())
        } catch (e: Exception) {
            val authorityCreateFailEvent = AuthorityCreateFailEvent(userCreatedEvent.userId)
            kafkaTemplate.send(
                "${AccessManagementEventRouter.USER_ERROR_TOPIC_PREFIX}-authority-creation",
                authorityCreateFailEvent
            )
        }
    }

    private fun DebeziumMessage.toUserCreatedEvent(): UserCreatedEvent {
        val messagePayload = this.after.payload
        return objectMapper.readValue(messagePayload, UserCreatedEvent::class.java)
    }
}
