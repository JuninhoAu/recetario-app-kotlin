package com.juni.recetarioapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juni.recetarioapp.data.local.database.RecipeEntity
import com.juni.recetarioapp.domain.usecase.InsertRecipeUseCase
import com.juni.recetarioapp.domain.usecase.RecipeListUseCase
import com.juni.recetarioapp.utils.error.ResultType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipeListUseCase: RecipeListUseCase,
    private val insertRecipeUseCase: InsertRecipeUseCase
) :
    ViewModel() {
    private val _recipeList = MutableLiveData<RecipeListState>()
    val recipeList: LiveData<RecipeListState> = _recipeList

    fun showRecipeList() {
        viewModelScope.launch {
            _recipeList.value = RecipeListState.Loading
            val result = getRecipeListUseCase.getList()
            when (result) {
                is ResultType.Success -> {
                    result.data.recipeList.map { it ->
                        with(it) {
                            insertRecipeUseCase.invoke(
                                RecipeEntity(
                                    nombre = nombre,
                                    imagen = imagen,
                                    descripcion = descripcion,
                                    ingredientes = ingredientes,
                                    pasos = pasos
                                )
                            )
                        }

                    }
                    _recipeList.value = RecipeListState.Success(result.data.recipeList)
                }

                is ResultType.Error -> {
                    _recipeList.value = RecipeListState.Error(result.error)
                }
            }
        }
    }
}