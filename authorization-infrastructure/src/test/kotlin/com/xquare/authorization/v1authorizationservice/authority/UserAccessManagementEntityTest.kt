package com.xquare.authorization.v1authorizationservice.authority

import com.xquare.authorization.v1authorizationservice.EqualsTestUtil
import com.xquare.authorization.v1authorizationservice.RepositoryIntegrationTest
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.UserAccessManagementEntity
import com.xquare.authorization.v1authorizationservice.authority.accessmanagement.repositories.UserAccessManagementRepository
import com.xquare.authorization.v1authorizationservice.authority.repositories.AuthorityRepository
import java.util.UUID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.context.TestConstructor

@ExperimentalCoroutinesApi
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RepositoryIntegrationTest
internal class UserAccessManagementEntityTest(
    private val userAccessManagementRepository: UserAccessManagementRepository,
    private val authorityRepository: AuthorityRepository
) {
    @Test
    fun `saveFailTest FK Integrity Violation`() = runTest {
        val userAccessManagementToSave = buildUserAccessManagement(UUID.randomUUID())

        assertThrows<DataIntegrityViolationException> {
            userAccessManagementRepository.save(userAccessManagementToSave)
        }
    }

    @Test
    fun updateSuccessTest() = runTest {
        val savedAuthority = saveAuthoritySetup()
        val userAccessManagementToSave = buildUserAccessManagement(savedAuthority.id)
        userAccessManagementRepository.save(userAccessManagementToSave)

        userAccessManagementRepository.save(userAccessManagementToSave)
    }

    @Test
    fun findByIdSuccessTest() = runTest {
        val savedAuthority = saveAuthoritySetup()
        val userAccessManagementToSave = buildUserAccessManagement(savedAuthority.id)
        userAccessManagementRepository.save(userAccessManagementToSave)

        val userAccessManagementFromDB =
            userAccessManagementRepository.findById(userAccessManagementToSave.id)

        EqualsTestUtil.isEqualTo(userAccessManagementToSave, userAccessManagementFromDB!!)
    }

    private fun buildUserAccessManagement(authorityId: UUID) =
        UserAccessManagementEntity(
            authorityId = authorityId,
            userId = UUID.randomUUID(),
        )

    private suspend fun saveAuthoritySetup() =
        authorityRepository.save(buildTestAuthority())

    private fun buildTestAuthority() =
        AuthorityEntity(
            name = "test1",
            description = "testest~",
            type = "test"
        )
}
