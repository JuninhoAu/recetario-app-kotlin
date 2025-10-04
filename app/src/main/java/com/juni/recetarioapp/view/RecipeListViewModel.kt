package com.juni.recetarioapp.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juni.recetarioapp.domain.usecase.GetRecipeListUseCase
import com.juni.recetarioapp.domain.usecase.UpdateRecipeUseCase
import com.juni.recetarioapp.utils.error.ResultType
import com.juni.recetarioapp.view.mapper.toDomain
import com.juni.recetarioapp.view.mapper.toPresentation
import com.juni.recetarioapp.view.model.RecipeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getGetRecipeListUseCase: GetRecipeListUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase,
) : ViewModel() {

    private val _getRecipeList = MutableStateFlow<RecipeListState>(RecipeListState.Idle)
    val getRecipeList: StateFlow<RecipeListState> = _getRecipeList

    fun showRecipeList() {
        viewModelScope.launch {
            _getRecipeList.value = RecipeListState.Loading

            getGetRecipeListUseCase.getList().collect { result ->
                when (result) {
                    is ResultType.Error -> {
                        _getRecipeList.value = RecipeListState.Error(result.error)
                    }

                    is ResultType.Success -> {
                        _getRecipeList.value =
                            RecipeListState.Success(result.data.map { it.toPresentation() })
                    }
                }
            }
        }
    }

    fun addRecipeItemToFav(recipe: RecipeModel) {
        viewModelScope.launch {
            updateRecipeUseCase.updateRecipe(
                recipe.toDomain().copy(favorito = !recipe.favorito)
            )
        }
    }
}