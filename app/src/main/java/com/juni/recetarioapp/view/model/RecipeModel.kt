package com.juni.recetarioapp.view.model

data class RecipeModel(
    val id: String,
    val nombre: String,
    val imagen: String,
    val descripcion: String,
    val ingredientes: List<String>,
    val pasos: List<String>,
    val favorito: Boolean
)