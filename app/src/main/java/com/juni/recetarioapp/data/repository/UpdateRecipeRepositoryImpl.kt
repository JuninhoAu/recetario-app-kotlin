package com.juni.recetarioapp.data.repository

import com.juni.recetarioapp.data.local.datasource.RecipeLocalDataSource
import com.juni.recetarioapp.data.local.preferences.UserPreferences
import com.juni.recetarioapp.data.mapper.toEntity
import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.domain.repository.UpdateRecipeRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpdateRecipeRepositoryImpl @Inject constructor(
    private val localDataSource: RecipeLocalDataSource,
    private val userPreferences: UserPreferences
) : UpdateRecipeRepository {

    override suspend fun updateRecipe(recipe: Recipe) {
        localDataSource.updateRecipe(recipeEntity = recipe.toEntity())
        if (!recipe.favorito) {
            userPreferences.removeFavoriteId(recipeId = recipe.id)
        } else {
            val favListIdLocal = localDataSource.getAllFavorite().first().map { it.id }.toSet()
            userPreferences.addFavoriteId(recipesId = favListIdLocal)
        }
    }
}
