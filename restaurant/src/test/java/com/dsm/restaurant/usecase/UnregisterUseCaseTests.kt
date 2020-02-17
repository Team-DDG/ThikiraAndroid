package com.dsm.restaurant.usecase

import com.dsm.restaurant.data.error.exception.ForbiddenException
import com.dsm.restaurant.data.error.exception.UnauthorizedException
import com.dsm.restaurant.domain.interactor.UnregisterUseCase
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
class UnregisterUseCaseTests {

    @Mock
    private lateinit var authRepository: AuthRepository

    @Mock
    private lateinit var accountRepository: AccountRepository

    private lateinit var usecase: UnregisterUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        usecase = UnregisterUseCase(accountRepository, authRepository)
    }

    @Test
    fun unregisterSuccessTest() = runBlocking {
        `when`(authRepository.confirmPassword("PASSWORD")).thenReturn(Unit)
        `when`(accountRepository.unregister()).thenReturn(Unit)
        usecase.invoke("PASSWORD")

        verify(authRepository).confirmPassword("PASSWORD")
        verify(accountRepository).unregister()
    }

    @Test
    fun authPasswordFailedTest() = runBlocking {
        `when`(authRepository.confirmPassword("PASSWORD"))
            .thenThrow(UnauthorizedException(Exception()))

        try {
            usecase.invoke("PASSWORD")
        } catch (e: Exception) {
            assert(e is UnauthorizedException)
        } finally {
            verifyNoInteractions(accountRepository)
        }
    }

    @Test
    fun unregisterFailedTest() = runBlocking {
        `when`(authRepository.confirmPassword("PASSWORD")).thenReturn(Unit)
        `when`(accountRepository.unregister()).thenThrow(ForbiddenException(Exception()))

        try {
            usecase.invoke("PASSWORD")
        } catch (e: Exception) {
            assert(e is ForbiddenException)
        } finally {
            verify(authRepository).confirmPassword("PASSWORD")
        }
    }
}