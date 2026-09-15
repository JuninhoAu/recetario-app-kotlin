package com.juni.recetarioapp.data.repository

import com.juni.recetarioapp.data.mapper.toDomain
import com.juni.recetarioapp.data.mapper.toEntity
import com.juni.recetarioapp.data.local.datasource.RecipeLocalDataSource
import com.juni.recetarioapp.data.local.preferences.UserPreferences
import com.juni.recetarioapp.data.network.datasource.RecipeRemoteDataSource
import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.domain.repository.GetRecipeListRepository
import com.juni.recetarioapp.utils.error.Failure
import com.juni.recetarioapp.utils.error.ResultType
import com.juni.recetarioapp.utils.error.errorUtilResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map


class GetRecipeListRepositoryImpl @Inject constructor(
    private val remoteDataSource: RecipeRemoteDataSource,
    private val localDataSource: RecipeLocalDataSource,
    private val preferencesDataSource: UserPreferences
) :
    GetRecipeListRepository {
    override fun getListRecipe(): Flow<ResultType<List<Recipe>, Failure>> =
        getAndSyncRecipes().flowOn(Dispatchers.IO)


    private fun getAndSyncRecipes(): Flow<ResultType<List<Recipe>, Failure>> = flow {
        try {
            val response = remoteDataSource.getRecipeList()

            if (!response.isSuccessful) {
                emit(ResultType.Error(Failure.NetworkFailure("response error")))
                return@flow
            }

            response.body()?.let {
                if (it.recipeList.isEmpty()) {
                    emit(ResultType.Error(Failure.ApiFailure("body is empty")))
                    return@flow
                }
                val favoriteIds = preferencesDataSource.getFavoriteIds().first()

                val entities = it.recipeList.map { recipeResponse ->
                    recipeResponse.toEntity().copy(favorito = recipeResponse.id in favoriteIds)
                }

                localDataSource.insertRecipes(entities)

            } ?: run {
                emit(ResultType.Error(Failure.ApiFailure("body is null")))
                return@flow
            }

            emitAll(localDataSource.getRecipeList().map {
                ResultType.Success(it.map { recipeEntity -> recipeEntity.toDomain() })
            })

        } catch (t: Throwable) {
            emit(ResultType.Error(errorUtilResponse.errorHandler(t)))
        }
    }
}
