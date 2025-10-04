package com.juni.recetarioapp.domain.usecase

import com.juni.recetarioapp.domain.repository.OnboardingRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class OnboardingUseCaseTest {

    private lateinit var repository: OnboardingRepository
    private lateinit var useCase: OnboardingUseCase

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        useCase = OnboardingUseCase(repository)
    }

    @Test
    fun `given repository returns true when isCompleteOnboarding is called then return success result`() =
        runTest {
            val isOnboardingCompleted = true
            coEvery { repository.isOnboardingCompleted() } returns flow {
                emit(isOnboardingCompleted)
            }
            val result = useCase.isCompleteOnboarding().first()

            assertEquals(true, result)
        }

    @Test
    fun `given true when setOnboardingComplete is called then repository is called`() =
        runTest {
            val isComplete = true

            useCase.setOnboardingComplete(isComplete)

            coVerify(exactly = 1) { repository.setOnboardingCompleted(isComplete) }
        }
}