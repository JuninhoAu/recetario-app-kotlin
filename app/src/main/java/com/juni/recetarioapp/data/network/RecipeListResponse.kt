package com.juni.recetarioapp.data.network

data class RecipeListResponse(
    val recipeList: List<RecipeResponse>
)

data class RecipeResponse(
    val nombre: String,
    val imagen: String,
    val ingredientes: List<String>,
    val pasos: List<String>,
    val descripcion: String
)