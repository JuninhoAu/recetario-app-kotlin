package com.juni.recetarioapp.view.recipeitemlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juni.recetarioapp.domain.usecase.GetRecipeListUseCase
import com.juni.recetarioapp.domain.usecase.ToggleFavoriteUseCase
import com.juni.recetarioapp.utils.error.ResultType
import com.juni.recetarioapp.view.mapper.toDomain
import com.juni.recetarioapp.view.mapper.toPresentation
import com.juni.recetarioapp.view.model.RecipeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipeListUseCase: GetRecipeListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {

    // private val _recipeListState = MutableStateFlow<RecipeListState>(RecipeListState.Idle)
    // val recipeListState: StateFlow<RecipeListState> = _recipeListState

    /* fun loadRecipes() {
         viewModelScope.launch(Dispatchers.Default) {

             getRecipeListUseCase().collect { result ->
                 when (result) {
                     is ResultType.Error -> {
                         _recipeListState.value = RecipeListState.Error(result.error)
                     }

                     is ResultType.Success -> {
                         _recipeListState.value =
                             RecipeListState.Success(result.data.map { it.toPresentation() })
                     }
                 }
             }
         }
     }*/

    val recipeListState: StateFlow<RecipeListState> = getRecipeListUseCase()
        .map { result ->
            when (result) {
                is ResultType.Error -> RecipeListState.Error(result.error)
                is ResultType.Success -> RecipeListState.Success(result.data.map { it.toPresentation() })
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = RecipeListState.Loading
        )

    fun toggleFavorite(recipe: RecipeModel) {
        viewModelScope.launch {
            toggleFavoriteUseCase(
                recipe.toDomain().copy(favorito = !recipe.favorito)
            )
        }
    }
}