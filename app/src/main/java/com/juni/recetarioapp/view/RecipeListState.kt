package com.juni.recetarioapp.view

import com.juni.recetarioapp.data.network.RecipeResponse
import com.juni.recetarioapp.utils.error.Failure

sealed class RecipeListState {
    data object Idle : RecipeListState()
    data object Loading : RecipeListState()
    data class Success(val recipe: List<RecipeResponse>) : RecipeListState()
    data class Error(val error: Failure) : RecipeListState()
}