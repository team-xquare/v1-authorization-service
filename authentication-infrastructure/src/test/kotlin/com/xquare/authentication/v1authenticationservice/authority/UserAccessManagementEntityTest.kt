package com.xquare.authentication.v1authenticationservice.authority

import com.xquare.authentication.v1authenticationservice.EqualsTestUtil
import com.xquare.authentication.v1authenticationservice.RepositoryIntegrationTest
import com.xquare.authentication.v1authenticationservice.authority.accessmanagement.UserAccessManagementEntity
import com.xquare.authentication.v1authenticationservice.authority.accessmanagement.repositories.UserAccessManagementRepository
import com.xquare.authentication.v1authenticationservice.authority.repositories.AuthorityRepository
import java.util.UUID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.reactor.awaitSingle
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
            userAccessManagementRepository.save(userAccessManagementToSave).awaitSingle()
        }
    }

    @Test
    fun updateSuccessTest() = runTest {
        val savedAuthority = saveAuthoritySetup()
        val userAccessManagementToSave = buildUserAccessManagement(savedAuthority.id)
        userAccessManagementRepository.save(userAccessManagementToSave).awaitSingle()

        userAccessManagementRepository.save(userAccessManagementToSave).awaitSingle()
    }

    @Test
    fun findByIdSuccessTest() = runTest {
        val savedAuthority = saveAuthoritySetup()
        val userAccessManagementToSave = buildUserAccessManagement(savedAuthority.id)
        userAccessManagementRepository.save(userAccessManagementToSave).awaitSingle()

        val userAccessManagementFromDB =
            userAccessManagementRepository.findById(userAccessManagementToSave.id).awaitSingle()

        EqualsTestUtil.isEqualTo(userAccessManagementToSave, userAccessManagementFromDB)
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
            id = UUID.randomUUID()
        )
}
