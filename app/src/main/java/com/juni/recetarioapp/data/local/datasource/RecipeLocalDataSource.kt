package com.juni.recetarioapp.data.local.datasource

import com.juni.recetarioapp.data.local.database.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipeLocalDataSource {
    fun getRecipeList(): Flow<List<RecipeEntity>>
    suspend fun insertRecipe(recipeEntity: RecipeEntity)
    suspend fun updateRecipe(recipeEntity: RecipeEntity)
    suspend fun getAllFavorite(): Flow<List<RecipeEntity>>
}