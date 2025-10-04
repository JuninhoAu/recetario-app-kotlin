package com.juni.recetarioapp.domain.usecase

import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.domain.repository.UpdateRecipeRepository
import javax.inject.Inject

class UpdateRecipeUseCase @Inject constructor(private val updateRecipeRepository: UpdateRecipeRepository) {
    suspend fun updateRecipe(recipe: Recipe) {
        updateRecipeRepository.updateRecipe(recipe)
    }
}