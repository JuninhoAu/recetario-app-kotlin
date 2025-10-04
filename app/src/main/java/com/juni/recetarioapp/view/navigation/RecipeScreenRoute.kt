package com.juni.recetarioapp.view.navigation

sealed class RecipeScreenRoute(val route: String) {
    object Onboarding : RecipeScreenRoute("onboarding")
    object List : RecipeScreenRoute("recipeListScreen")
    object Detail : RecipeScreenRoute("recipeItemDetailScreen?item={encodedItem}") {
        fun createRoute(encodedItem: String) = "recipeItemDetailScreen?item=$encodedItem"
    }
}