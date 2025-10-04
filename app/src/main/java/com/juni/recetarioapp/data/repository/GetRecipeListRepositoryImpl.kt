package com.juni.recetarioapp.data.repository

import com.juni.recetarioapp.data.mapper.toDomain
import com.juni.recetarioapp.data.mapper.toEntity
import com.juni.recetarioapp.data.local.datasource.RecipeLocalDataSource
import com.juni.recetarioapp.data.network.datasource.RecipeRemoteDataSource
import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.domain.repository.GetRecipeListRepository
import com.juni.recetarioapp.utils.error.Failure
import com.juni.recetarioapp.utils.error.ResultType
import com.juni.recetarioapp.utils.error.errorUtilResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class GetRecipeListRepositoryImpl @Inject constructor(
    private val remoteDataSource: RecipeRemoteDataSource,
    private val localDataSource: RecipeLocalDataSource
) :
    GetRecipeListRepository {
    override suspend fun getListRecipe(): Flow<ResultType<List<Recipe>, Failure>> = flow {
        try {
            val response = remoteDataSource.getRecipeList()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let { recipeListResponse ->
                    if (recipeListResponse.recipeList.isNotEmpty()) {
                        recipeListResponse.recipeList.map { localDataSource.insertRecipe(it.toEntity()) }
                        localDataSource.getRecipeList()
                            .map { list -> list.map { it.toDomain() } }
                            .collect {
                                emit(ResultType.Success(it))
                            }
                    } else {
                        emit(ResultType.Error(Failure.ApiFailure("lista vacia")))
                    }
                } ?: run {
                    emit(ResultType.Error(Failure.ApiFailure("body is null")))
                }
            } else {
                emit(ResultType.Error(Failure.NetworkFailure("response error")))
            }
        } catch (t: Throwable) {
            emit(ResultType.Error(errorUtilResponse.errorHandler(t)))
        }
    }.flowOn(Dispatchers.IO)
}