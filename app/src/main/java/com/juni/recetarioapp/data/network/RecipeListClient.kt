package com.juni.recetarioapp.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeListClient {
    @GET("recipeListV1")
    suspend fun recipeListClient(
        @Query("recipeId") recipeId: String
    ): Response<RecipeListResponse>
}