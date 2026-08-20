package com.juni.recetarioapp.domain.usecase

import com.juni.recetarioapp.domain.model.Recipe
import com.juni.recetarioapp.domain.repository.GetRecipeListRepository
import com.juni.recetarioapp.utils.error.Failure
import com.juni.recetarioapp.utils.error.ResultType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetRecipeListUseCase @Inject constructor(private val repository: GetRecipeListRepository) {
    suspend fun getList(): Flow<ResultType<List<Recipe>, Failure>> {
        return repository.getListRecipe()
    }
}