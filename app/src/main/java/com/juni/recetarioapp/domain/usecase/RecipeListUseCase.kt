package com.juni.recetarioapp.domain.usecase

import com.juni.recetarioapp.data.network.RecipeListResponse
import com.juni.recetarioapp.domain.repository.RecipeListRepository
import com.juni.recetarioapp.utils.error.Failure
import com.juni.recetarioapp.utils.error.ResultType
import javax.inject.Inject


class RecipeListUseCase @Inject constructor(private val repository: RecipeListRepository) {
    suspend fun getList(): ResultType<RecipeListResponse, Failure> {
        return repository.getListRecipe("1")
    }
}