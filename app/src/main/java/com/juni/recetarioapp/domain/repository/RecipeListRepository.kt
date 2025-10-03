package com.juni.recetarioapp.domain.repository

import com.juni.recetarioapp.data.network.RecipeListResponse
import com.juni.recetarioapp.utils.error.Failure
import com.juni.recetarioapp.utils.error.ResultType

interface RecipeListRepository {
    suspend fun getListRecipe(id:String): ResultType<RecipeListResponse, Failure>
}