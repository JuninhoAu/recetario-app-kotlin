package com.juni.recetarioapp.data.network.datasource

import com.juni.recetarioapp.data.network.api.RecipeListClient
import com.juni.recetarioapp.data.network.model.RecipeListResponse
import retrofit2.Response
import javax.inject.Inject

class RecipeRemoteDataSourceImpl @Inject constructor(private val listClient: RecipeListClient) :
    RecipeRemoteDataSource {
    override suspend fun getRecipeList(): Response<RecipeListResponse> {
        return listClient.recipeListClient()
    }
}