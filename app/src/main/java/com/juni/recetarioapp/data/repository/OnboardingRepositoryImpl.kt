package com.juni.recetarioapp.data.repository

import com.juni.recetarioapp.data.local.preferences.UserPreferences
import com.juni.recetarioapp.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(private val userPreferences: UserPreferences) :
    OnboardingRepository {

    override fun isOnboardingCompleted(): Flow<Boolean> {
        return userPreferences.isOnboardingCompleted()
    }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        userPreferences.setOnboardingCompleted(completed)
    }
}