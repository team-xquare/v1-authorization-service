package com.xquare.authorization.domain.authority

import com.xquare.authorization.domain.annotations.Aggregate
import java.util.UUID

@Aggregate
class Authority(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String
)
