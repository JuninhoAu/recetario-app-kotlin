package com.juni.recetarioapp.data.mapper

import com.juni.recetarioapp.data.local.database.RecipeEntity
import com.juni.recetarioapp.data.network.RecipeResponse
import com.juni.recetarioapp.domain.model.Recipe

fun RecipeResponse.toEntity(): RecipeEntity {
    return RecipeEntity(
        nombre = this.nombre,
        imagen = this.imagen,
        descripcion = this.descripcion,
        ingredientes = this.ingredientes,
        pasos = this.pasos
    )
}

fun RecipeEntity.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        nombre = this.nombre,
        imagen = this.imagen,
        descripcion = this.descripcion,
        ingredientes = this.ingredientes,
        pasos = this.pasos,
        favorito = this.favorito
    )
}

fun Recipe.toEntity(): RecipeEntity {
    return RecipeEntity(
        id = this.id,
        nombre = this.nombre,
        imagen = this.imagen,
        descripcion = this.descripcion,
        ingredientes = this.ingredientes,
        pasos = this.pasos,
        favorito = this.favorito
    )
}