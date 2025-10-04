package com.juni.recetarioapp.domain.repository

import com.juni.recetarioapp.data.local.database.RecipeEntity

interface RecipeBdRepository {
    suspend fun getAllRecipes(): List<RecipeEntity>
    suspend fun insertRecipe(recipe: RecipeEntity)
    suspend fun updateRecipe(recipe: RecipeEntity)
}