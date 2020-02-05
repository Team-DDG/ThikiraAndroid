package com.dsm.restaurant.usecase

import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.error.exception.UnauthorizedException
import com.dsm.restaurant.domain.interactor.ChangePwdUseCase
import com.dsm.restaurant.domain.repository.AccountRepository
import com.dsm.restaurant.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ChangePwdUseCaseTests {

    @Mock
    private lateinit var accountRepository: AccountRepository

    @Mock
    private lateinit var authRepository: AuthRepository

    private lateinit var usecase: ChangePwdUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        usecase = ChangePwdUseCase(accountRepository, authRepository)
    }

    @Test
    fun unregisterSuccessTest() = runBlockingTest {
        `when`(authRepository.authPassword("PASSWORD")).thenReturn(Unit)
        `when`(accountRepository.changePassword("NEW_PASSWORD")).thenReturn(Unit)
        usecase.invoke("PASSWORD", "NEW_PASSWORD")

        verify(authRepository).authPassword("PASSWORD")
        verify(accountRepository).changePassword("NEW_PASSWORD")
    }

    @Test
    fun authPasswordFailedTest() = runBlockingTest {
        `when`(authRepository.authPassword("PASSWORD"))
            .thenThrow(UnauthorizedException(Exception()))

        try {
            usecase.invoke("PASSWORD", "NEW_PASSWORD")
        } catch (e: Exception) {
            assert(e is UnauthorizedException)
        } finally {
            verifyNoInteractions(accountRepository)
        }
    }

    @Test
    fun unregisterFailedTest() = runBlockingTest {
        `when`(authRepository.authPassword("PASSWORD")).thenReturn(Unit)
        `when`(accountRepository.changePassword("NEW_PASSWORD")).thenThrow(ForbiddenException(Exception()))

        try {
            usecase.invoke("PASSWORD", "NEW_PASSWORD")
        } catch (e: Exception) {
            assert(e is ForbiddenException)
        } finally {
            verify(authRepository).authPassword("PASSWORD")
        }
    }
}