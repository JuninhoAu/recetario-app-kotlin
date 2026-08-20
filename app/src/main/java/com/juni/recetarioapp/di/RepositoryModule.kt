package com.juni.recetarioapp.di

import com.juni.recetarioapp.data.local.datasource.RecipeLocalDataSource
import com.juni.recetarioapp.data.local.datasource.RecipeLocalDataSourceImpl
import com.juni.recetarioapp.data.network.datasource.RecipeRemoteDataSource
import com.juni.recetarioapp.data.network.datasource.RecipeRemoteDataSourceImpl
import com.juni.recetarioapp.data.repository.OnboardingRepositoryImpl
import com.juni.recetarioapp.data.repository.UpdateRecipeRepositoryImpl
import com.juni.recetarioapp.data.repository.GetRecipeListRepositoryImpl
import com.juni.recetarioapp.domain.repository.OnboardingRepository
import com.juni.recetarioapp.domain.repository.UpdateRecipeRepository
import com.juni.recetarioapp.domain.repository.GetRecipeListRepository
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
    abstract fun getRecipeListRepository(impl: GetRecipeListRepositoryImpl): GetRecipeListRepository

    @Binds
    @Singleton
    abstract fun getOnboardingRepository(impl: OnboardingRepositoryImpl): OnboardingRepository

    @Binds
    @Singleton
    abstract fun getUpdateRecipeRepository(impl: UpdateRecipeRepositoryImpl): UpdateRecipeRepository

    @Binds
    @Singleton
    abstract fun getRecipeRemoteDataSource(impl: RecipeRemoteDataSourceImpl): RecipeRemoteDataSource

    @Binds
    @Singleton
    abstract fun getRecipeLocalDataSource(impl: RecipeLocalDataSourceImpl): RecipeLocalDataSource
}