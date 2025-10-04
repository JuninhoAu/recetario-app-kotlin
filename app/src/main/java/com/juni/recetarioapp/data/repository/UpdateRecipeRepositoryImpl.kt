package com.juni.recetarioapp.data.repository

import com.juni.recetarioapp.data.local.database.RecipeDao
import com.juni.recetarioapp.data.mapper.toEntity
import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.domain.repository.UpdateRecipeRepository
import javax.inject.Inject

class UpdateRecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
) : UpdateRecipeRepository {

    override suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe = recipe.toEntity())
    }
}
