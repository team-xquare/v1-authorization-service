package com.xquare.authorization.v1authorizationservice.authority.repositories

import com.xquare.authorization.v1authorizationservice.authority.AuthorityEntity
import java.util.UUID
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param

interface AuthorityRepository : CoroutineCrudRepository<AuthorityEntity, UUID> {
    suspend fun findAllByNameIn(names: List<String>): List<AuthorityEntity>

    @Query("""
        select id, name, description
        from tbl_authority
        inner join tbl_access_management_user
            on tbl_access_management_user.authority_id = tbl_authority.id
        where tbl_access_management_user.user_id = :userId
    """)
    suspend fun findAllByUserId(@Param("userId") userId: UUID): List<AuthorityEntity>
}
