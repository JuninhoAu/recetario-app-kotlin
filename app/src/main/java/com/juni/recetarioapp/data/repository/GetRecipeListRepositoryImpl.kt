package com.juni.recetarioapp.data.repository

import android.util.Log
import com.juni.recetarioapp.data.mapper.toDomain
import com.juni.recetarioapp.data.mapper.toEntity
import com.juni.recetarioapp.data.local.datasource.RecipeLocalDataSource
import com.juni.recetarioapp.data.local.preferences.UserPreferences
import com.juni.recetarioapp.data.network.datasource.RecipeRemoteDataSource
import com.juni.recetarioapp.data.network.model.RecipeResponse
import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.domain.repository.GetRecipeListRepository
import com.juni.recetarioapp.utils.error.Failure
import com.juni.recetarioapp.utils.error.ResultType
import com.juni.recetarioapp.utils.error.errorUtilResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import kotlin.collections.map


class GetRecipeListRepositoryImpl @Inject constructor(
    private val remoteDataSource: RecipeRemoteDataSource,
    private val localDataSource: RecipeLocalDataSource,
    private val preferencesDataSource: UserPreferences
) :
    GetRecipeListRepository {
    override fun getListRecipe(): Flow<ResultType<List<Recipe>, Failure>> =
        localDataSource.getRecipeList()
            .map { entities ->
                ResultType.Success(entities.map { it.toDomain() })
            }
            .onStart { refreshRecipes() }
            //.flowOn(Dispatchers.IO)


    suspend fun refreshRecipes() {
        val result = fetchRemoteRecipes()
        when (result) {
            is ResultType.Success -> {
                val favoriteIds = preferencesDataSource.getFavoriteIds().first()
                val entities = result.data.map { recipeResponse ->
                    recipeResponse.toEntity().copy(favorito = recipeResponse.id in favoriteIds)
                }
                localDataSource.insertRecipes(entities)
            }

            is ResultType.Error -> {
                Log.w("GetRecipeListRepository", "Error refreshing recipes ${result.error}")
            }
        }
    }

    private suspend fun fetchRemoteRecipes(): ResultType<List<RecipeResponse>, Failure> {
        try {
            val response = remoteDataSource.getRecipeList()

            if (!response.isSuccessful) {
                return ResultType.Error(Failure.NetworkFailure("HTTP error ${response.code()}"))
            }

            val recipes =
                response.body()?.recipeList
                    ?: return ResultType.Error(Failure.ApiFailure("response body is null"))

            return ResultType.Success(recipes)

        } catch (t: Throwable) {
            return ResultType.Error(errorUtilResponse.errorHandler(t))
        }
    }
}
