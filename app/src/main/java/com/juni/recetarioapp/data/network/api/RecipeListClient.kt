package com.juni.recetarioapp.data.network.api

import com.juni.recetarioapp.data.network.model.RecipeListResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipeListClient {
    @GET("recipeListV1")
    suspend fun recipeListClient(): Response<RecipeListResponse>
}