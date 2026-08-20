package com.juni.recetarioapp.domain.repository

import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.utils.error.Failure
import com.juni.recetarioapp.utils.error.ResultType
import kotlinx.coroutines.flow.Flow

interface GetRecipeListRepository {
    suspend fun getListRecipe(): Flow<ResultType<List<Recipe>, Failure>>
}