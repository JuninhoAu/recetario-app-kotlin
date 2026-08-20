package com.juni.recetarioapp.domain.usecase

import com.juni.recetarioapp.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingUseCase @Inject constructor(private val onboardingRepository: OnboardingRepository) {
    suspend fun setOnboardingComplete(complete: Boolean) {
        onboardingRepository.setOnboardingCompleted(completed = complete)
    }

    fun isCompleteOnboarding(): Flow<Boolean> = onboardingRepository.isOnboardingCompleted()

}