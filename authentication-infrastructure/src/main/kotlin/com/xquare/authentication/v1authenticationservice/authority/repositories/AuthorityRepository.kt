package com.xquare.authentication.v1authenticationservice.authority.repositories

import com.xquare.authentication.domain.authority.AuthorityState
import com.xquare.authentication.v1authenticationservice.authority.AuthorityEntity
import java.util.UUID
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthorityRepository : CoroutineCrudRepository<AuthorityEntity, UUID> {
    suspend fun findByIdAndState(id: UUID, state: AuthorityState): AuthorityEntity?
    suspend fun findAllByNameIn(names: List<String>): List<AuthorityEntity>
}
