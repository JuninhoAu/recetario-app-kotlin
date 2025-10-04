package com.juni.recetarioapp.domain.usecase

import com.juni.recetarioapp.data.local.database.RecipeEntity
import com.juni.recetarioapp.domain.repository.RecipeBdRepository
import javax.inject.Inject

class InsertRecipeUseCase @Inject constructor(private val recipeBdRepository: RecipeBdRepository) {
    suspend operator fun invoke(recipeEntity: RecipeEntity) {
        recipeBdRepository.insertRecipe(recipeEntity)
    }
}