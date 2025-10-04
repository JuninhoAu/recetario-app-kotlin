package com.juni.recetarioapp.data.local.datasource

import com.juni.recetarioapp.data.local.database.RecipeDao
import com.juni.recetarioapp.data.local.database.RecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeLocalDataSourceImpl @Inject constructor(private val recipeDao: RecipeDao) :
    RecipeLocalDataSource {
    override fun getRecipeList(): Flow<List<RecipeEntity>> {
        return recipeDao.getAllRecipes()
    }

    override suspend fun insertRecipe(recipeEntity: RecipeEntity) {
        recipeDao.insertRecipe(recipe = recipeEntity)
    }

    override suspend fun updateRecipe(recipeEntity: RecipeEntity) {
        recipeDao.updateRecipe(recipe = recipeEntity)
    }
}