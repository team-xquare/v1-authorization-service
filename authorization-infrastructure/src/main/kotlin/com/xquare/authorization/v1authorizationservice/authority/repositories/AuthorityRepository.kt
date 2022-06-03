package com.xquare.authorization.v1authorizationservice.authority.repositories

import com.xquare.authorization.v1authorizationservice.authority.AuthorityEntity
import java.util.UUID
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthorityRepository : CoroutineCrudRepository<AuthorityEntity, UUID> {
    suspend fun findAllByNameIn(names: List<String>): List<AuthorityEntity>
}
