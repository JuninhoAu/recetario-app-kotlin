package com.juni.recetarioapp.data.repository

import com.juni.recetarioapp.data.local.datasource.RecipeLocalDataSource
import com.juni.recetarioapp.data.local.preferences.UserPreferences
import com.juni.recetarioapp.data.mapper.toEntity
import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val localDataSource: RecipeLocalDataSource,
    private val userPreferences: UserPreferences
) : FavoriteRepository {

    override suspend fun toggleFavorite(recipe: Recipe) {
        localDataSource.updateRecipeFavorite(recipeEntity = recipe.toEntity())
        if (!recipe.favorito) {
            userPreferences.removeFavoriteId(recipeId = recipe.id)
        } else {
            userPreferences.addFavoriteId(recipeId = recipe.id)
        }
    }
}
