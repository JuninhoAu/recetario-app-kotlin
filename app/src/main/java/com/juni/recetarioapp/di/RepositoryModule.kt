package com.juni.recetarioapp.di

import com.juni.recetarioapp.data.repository.RecipeListRepositoryImpl
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
}