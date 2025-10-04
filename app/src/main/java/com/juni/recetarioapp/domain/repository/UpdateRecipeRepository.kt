package com.juni.recetarioapp.domain.repository

import com.juni.recetarioapp.domain.model.Recipe

interface UpdateRecipeRepository {
    suspend fun updateRecipe(recipe: Recipe)
}