package com.juni.recetarioapp.data.repository

import com.juni.recetarioapp.data.network.RecipeListClient
import com.juni.recetarioapp.data.network.RecipeListResponse
import com.juni.recetarioapp.domain.repository.RecipeListRepository
import com.juni.recetarioapp.utils.error.Failure
import com.juni.recetarioapp.utils.error.ResultType
import com.juni.recetarioapp.utils.error.errorUtilResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class RecipeListRepositoryImpl @Inject constructor(private val recipeListClient: RecipeListClient) :
    RecipeListRepository {
    override suspend fun getListRecipe(id: String): ResultType<RecipeListResponse, Failure> {
        return withContext(Dispatchers.IO) {
            try {
                val response = recipeListClient.recipeListClient(recipeId = id)
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let { recipeListResponse ->
                        if (body.recipeList.isNotEmpty()) {
                            ResultType.Success(recipeListResponse)
                        } else {
                            ResultType.Error(Failure.ApiFailure("lista vacia"))
                        }
                    } ?: run {
                        ResultType.Error(Failure.ApiFailure("body is null"))
                    }
                } else {
                    ResultType.Error(Failure.NetworkFailure("response error"))
                }
            } catch (t: Throwable) {
                ResultType.Error(errorUtilResponse.errorHandler(t))
            }
        }

    }
}