package com.xquare.authorization.v1authorizationservice.authority.accessmanagement.router

import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.handler.UserCreateEventHandler
import com.xquare.authorization.v1authorizationservice.configuration.debezium.DebeziumMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaListener

@Configuration
class AccessManagementEventRouter(
    private val kafkaCoroutineScope: CoroutineScope,
    private val userCreateEventHandler: UserCreateEventHandler
) {
    companion object {
        const val USER_CREATED_TOPIC = "xquare-debezium.user.tbl_outbox"
        const val USER_ERROR_TOPIC_PREFIX = "user-error"
    }

    @KafkaListener(topics = [USER_CREATED_TOPIC], groupId = "authorization-service")
    fun consumeUserCreatedEvent(debeziumMessage: DebeziumMessage) = kafkaCoroutineScope.launch {
        userCreateEventHandler.createUserBaseAuthority(debeziumMessage)
    }
}
