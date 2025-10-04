package com.juni.recetarioapp.data.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey
    val id: String,
    val nombre: String,
    val imagen: String,
    val descripcion: String,
    val ingredientes: List<String>,
    val pasos: List<String>,
    val favorito: Boolean = false,
)

