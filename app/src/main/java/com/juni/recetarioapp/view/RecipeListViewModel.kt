package com.juni.recetarioapp.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val _recipeList = MutableLiveData<RecipeListState>()
    val recipeList: LiveData<RecipeListState> = _recipeList

    fun showRecipeList() {
        viewModelScope.launch {
            _recipeList.value = RecipeListState.Loading
            val result = useCase.getList()
            when (result) {
                is ResultType.Success -> {
                    _recipeList.value = RecipeListState.Success(result.data.recipeList)
                    Log.d("response: ", "tengo este success:" + result.data.recipeList.size)
                }

                is ResultType.Error -> {
                    _recipeList.value = RecipeListState.Error(result.error)
                    Log.d("response: ", "tengo este error:" + result.error.message)
                }
            }
        }
    }
}