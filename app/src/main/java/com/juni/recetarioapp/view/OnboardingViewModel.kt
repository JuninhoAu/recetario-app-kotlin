package com.juni.recetarioapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juni.recetarioapp.domain.usecase.OnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val onboardingUseCase: OnboardingUseCase) :
    ViewModel() {
    val onboardingCompleted = onboardingUseCase.isCompleteOnboarding()

    fun completeOnboarding() {
        viewModelScope.launch {
            onboardingUseCase.setOnboardingComplete(true)
        }
    }
}