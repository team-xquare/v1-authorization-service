package com.xquare.authorization.v1authorizationservice.outbox.repository

import com.xquare.authorization.v1authorizationservice.outbox.OutboxEntity
import java.util.UUID
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface OutboxRepository : ReactiveCrudRepository<OutboxEntity, UUID>
