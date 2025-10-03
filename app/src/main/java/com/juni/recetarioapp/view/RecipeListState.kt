package com.juni.recetarioapp.view

import com.juni.recetarioapp.utils.error.Failure

sealed class RecipeListState {
    data object idle : RecipeListState()
    data object loading : RecipeListState()
    data class success(val recipe: List<String>) : RecipeListState()
    data class error(val error: Failure) : RecipeListState()
}