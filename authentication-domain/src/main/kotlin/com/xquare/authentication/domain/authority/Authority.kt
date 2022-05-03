package com.xquare.authentication.domain.authority

import com.xquare.authentication.domain.annotations.Aggregate
import java.util.UUID

@Aggregate
class Authority(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String,
    val state: AuthorityState
)
