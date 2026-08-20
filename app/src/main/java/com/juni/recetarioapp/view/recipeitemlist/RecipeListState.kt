package com.juni.recetarioapp.view.recipeitemlist

import com.juni.recetarioapp.utils.error.Failure
import com.juni.recetarioapp.view.model.RecipeModel

sealed class RecipeListState {
    data object Idle : RecipeListState()
    data object Loading : RecipeListState()
    data class Success(val recipeList: List<RecipeModel>) : RecipeListState()
    data class Error(val error: Failure) : RecipeListState()
}