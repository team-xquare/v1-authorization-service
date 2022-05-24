package com.xquare.authorization.v1authorizationservice.outbox

import java.time.LocalDateTime
import java.util.UUID
import org.springframework.data.relational.core.mapping.Table

@Table("tbl_outbox")
class OutboxEntity(
    val id: UUID,

    val aggregateId: UUID,

    val aggregateType: String,

    val payload: String,

    val timestamp: LocalDateTime,

    val type: String
)
