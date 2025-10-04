package com.juni.recetarioapp.data.network

import retrofit2.Response
import retrofit2.http.GET

interface RecipeListClient {
    @GET("recipeListV1")
    suspend fun recipeListClient(): Response<RecipeListResponse>
}