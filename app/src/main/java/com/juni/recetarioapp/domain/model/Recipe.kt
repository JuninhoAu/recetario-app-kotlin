package com.juni.recetarioapp.domain.model

data class Recipe(
    val id: Int,
    val nombre: String,
    val imagen: String,
    val descripcion: String,
    val ingredientes: List<String>,
    val pasos: List<String>,
    val favorito: Boolean
)