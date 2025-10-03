package com.juni.recetarioapp.view

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juni.recetarioapp.domain.usecase.OnboardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val onboardingUseCase: OnboardingUseCase) :
    ViewModel() {
    private var _isLoadingSplash = mutableStateOf(true)
    val isLoadingSplash: State<Boolean> = _isLoadingSplash

    private var _shouldShowOnboarding = mutableStateOf(true)
    val shouldShowOnboarding: State<Boolean> = _shouldShowOnboarding


    init {
        viewModelScope.launch {
            onboardingUseCase.isCompleteOnboarding().collect { completed ->
                _shouldShowOnboarding.value = !completed
                _isLoadingSplash.value = false
            }
        }
    }

    fun completeOnboarding() {
        viewModelScope.launch {
            onboardingUseCase.setOnboardingComplete(true)
        }
    }
}