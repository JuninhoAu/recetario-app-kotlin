package com.juni.recetarioapp.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juni.recetarioapp.domain.usecase.RecipeListUseCase
import com.juni.recetarioapp.utils.error.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(private val useCase: RecipeListUseCase) :
    ViewModel() {
    fun showRecipeList() {
        viewModelScope.launch {
            val result = useCase.getList()
            when (result) {
                is ResultType.Success -> {
                    Log.d("response: ", "tengo este success:" + result.data.recipeList.size)
                }

                is ResultType.Error -> {
                    Log.d("response: ", "tengo este error:" + result.error.message)
                }
            }
        }
    }
}