package com.juni.recetarioapp.data.repository

import com.juni.recetarioapp.data.local.preferences.OnboardingPreferences
import com.juni.recetarioapp.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(private val onboardingPreferences: OnboardingPreferences) :
    OnboardingRepository {

    override fun isOnboardingCompleted(): Flow<Boolean> {
        return onboardingPreferences.isOnboardingCompleted()
    }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        onboardingPreferences.setOnboardingCompleted(completed)
    }
}