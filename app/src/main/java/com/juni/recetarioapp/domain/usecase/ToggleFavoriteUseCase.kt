package com.juni.recetarioapp.domain.usecase

import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.domain.repository.FavoriteRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(private val favoriteRepository: FavoriteRepository) {
    suspend operator fun invoke(recipe: Recipe) {
        favoriteRepository.toggleFavorite(recipe)
    }
}