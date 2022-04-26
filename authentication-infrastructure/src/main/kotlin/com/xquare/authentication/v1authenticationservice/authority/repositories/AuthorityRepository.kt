package com.xquare.authentication.v1authenticationservice.authority.repositories

import com.xquare.authentication.v1authenticationservice.authority.AuthorityEntity
import java.util.UUID
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface AuthorityRepository: CoroutineCrudRepository<AuthorityEntity, UUID> {
    fun findByIdAndSt
}
