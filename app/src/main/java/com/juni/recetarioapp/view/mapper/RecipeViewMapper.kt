package com.juni.recetarioapp.view.mapper

import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.view.model.RecipeModel

fun Recipe.toPresentation(): RecipeModel {
    return RecipeModel(
        id = this.id,
        nombre = this.nombre,
        imagen = this.imagen,
        descripcion = this.descripcion,
        ingredientes = this.ingredientes,
        pasos = this.pasos,
        favorito = this.favorito
    )
}

fun RecipeModel.toDomain(): Recipe {
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