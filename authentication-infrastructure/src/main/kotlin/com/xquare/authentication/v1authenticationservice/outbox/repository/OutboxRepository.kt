package com.xquare.authentication.v1authenticationservice.outbox.repository

import com.xquare.authentication.v1authenticationservice.outbox.OutboxEntity
import java.util.UUID
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface OutboxRepository : ReactiveCrudRepository<OutboxEntity, UUID>
