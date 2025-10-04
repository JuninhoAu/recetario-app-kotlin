package com.juni.recetarioapp.data.repository

import com.juni.recetarioapp.data.local.database.RecipeDao
import com.juni.recetarioapp.data.local.database.RecipeEntity
import com.juni.recetarioapp.domain.repository.RecipeBdRepository
import javax.inject.Inject

class RecipeBdRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao
) : RecipeBdRepository {
    override suspend fun getAllRecipes(): List<RecipeEntity> {
        return emptyList()
    }

    override suspend fun insertRecipe(recipe: RecipeEntity) {
        recipeDao.insertRecipe(recipe = recipe)
    }

    override suspend fun updateRecipe(recipe: RecipeEntity) {
       // recipeDao.updateRecipe(recipe = recipe)
    }

}
