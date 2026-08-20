package com.juni.recetarioapp.data.network.datasource

import com.juni.recetarioapp.data.network.model.RecipeListResponse
import retrofit2.Response

interface RecipeRemoteDataSource {
    suspend fun getRecipeList(): Response<RecipeListResponse>
}