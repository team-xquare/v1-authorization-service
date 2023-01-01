package com.xquare.authorization.v1authorizationservice.authority.repositories

import com.xquare.authorization.v1authorizationservice.authority.AuthorityEntity
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import java.util.*

interface AuthorityRepository : CoroutineCrudRepository<AuthorityEntity, UUID> {
    suspend fun findAllByNameIn(names: List<String>): List<AuthorityEntity>

    @Query(
        """
        select tbl_authority.id, tbl_authority.name, tbl_authority.description
        from tbl_authority
        inner join tbl_access_management_user
            on tbl_access_management_user.authority_id = tbl_authority.id
        where tbl_access_management_user.user_id = :userId
        """
    )
    suspend fun findAllByUserId(@Param("userId") userId: UUID): List<AuthorityEntity>

    @Query(
        """
        select tbl_authority.id, tbl_authority.name, tbl_authority.description
        from tbl_authority
        inner join tbl_access_management_user
            on tbl_access_management_user.authority_id = tbl_authority.id
        where tbl_access_management_user.user_id = :userId AND tbl_authority.type = :type
        """
    )
    suspend fun findAllByUserIdAndType(
        @Param("userId") userId: UUID, @Param("type") type: String): List<AuthorityEntity>
}
