package com.juni.recetarioapp.di

import com.juni.recetarioapp.data.repository.OnboardingRepositoryImpl
import com.juni.recetarioapp.data.repository.RecipeBdRepositoryImpl
import com.juni.recetarioapp.data.repository.RecipeListRepositoryImpl
import com.juni.recetarioapp.domain.repository.OnboardingRepository
import com.juni.recetarioapp.domain.repository.RecipeBdRepository
import com.juni.recetarioapp.domain.repository.RecipeListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun getRecipeListRepository(impl: RecipeListRepositoryImpl): RecipeListRepository

    @Binds
    @Singleton
    abstract fun getOnboardingRepository(impl: OnboardingRepositoryImpl): OnboardingRepository

    @Binds
    @Singleton
    abstract fun getRecipeBdRepository(impl: RecipeBdRepositoryImpl): RecipeBdRepository
}