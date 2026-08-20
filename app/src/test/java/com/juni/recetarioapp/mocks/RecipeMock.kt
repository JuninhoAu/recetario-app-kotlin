package com.juni.recetarioapp.mocks

import com.juni.recetarioapp.domain.model.Recipe

fun getRecipeMock(): Recipe {
    return Recipe(
        id = "2",
        nombre = "Pizza",
        imagen = "una url",
        descripcion = "pizza grande familiar",
        pasos = listOf("comprar la pizza"),
        ingredientes = listOf("salsa"),
        favorito = false
    )
}