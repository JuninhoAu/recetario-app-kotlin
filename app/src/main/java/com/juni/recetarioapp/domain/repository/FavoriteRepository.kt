package com.juni.recetarioapp.domain.repository

import com.juni.recetarioapp.domain.model.Recipe

interface FavoriteRepository {
    suspend fun toggleFavorite(recipe: Recipe)
}