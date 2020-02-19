package com.dsm.restaurant.usecase

import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.error.exception.UnauthorizedException
import com.dsm.restaurant.domain.interactor.ChangePasswordUseCase
import com.dsm.restaurant.domain.repository.AccountRepository
import com.dsm.restaurant.domain.repository.AuthRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ChangePasswordUseCaseTests {

    @Mock
    private lateinit var accountRepository: AccountRepository

    @Mock
    private lateinit var authRepository: AuthRepository

    private lateinit var usecase: ChangePasswordUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        usecase = ChangePasswordUseCase(accountRepository, authRepository)
    }

    @Test
    fun unregisterSuccessTest() = runBlocking {
        `when`(authRepository.confirmPassword("PASSWORD")).thenReturn(Unit)
        `when`(accountRepository.changePassword("NEW_PASSWORD")).thenReturn(Unit)
        usecase.invoke("PASSWORD", "NEW_PASSWORD")

        verify(authRepository).confirmPassword("PASSWORD")
        verify(accountRepository).changePassword("NEW_PASSWORD")
    }

    @Test
    fun authPasswordFailedTest() = runBlocking {
        `when`(authRepository.confirmPassword("PASSWORD"))
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
    fun unregisterFailedTest() = runBlocking {
        `when`(authRepository.confirmPassword("PASSWORD")).thenReturn(Unit)
        `when`(accountRepository.changePassword("NEW_PASSWORD")).thenThrow(ForbiddenException(Exception()))

        try {
            usecase.invoke("PASSWORD", "NEW_PASSWORD")
        } catch (e: Exception) {
            assert(e is ForbiddenException)
        } finally {
            verify(authRepository).confirmPassword("PASSWORD")
        }
    }
}