package com.xquare.authentication.v1authenticationservice.authority.repositories

import com.xquare.authentication.v1authenticationservice.authority.AuthorityEntity
import com.xquare.authentication.v1authenticationservice.authority.AuthorityState
import java.util.UUID
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthorityRepository : CoroutineCrudRepository<AuthorityEntity, UUID> {
    fun findByIdAndState(id: UUID, state: AuthorityState)
}
