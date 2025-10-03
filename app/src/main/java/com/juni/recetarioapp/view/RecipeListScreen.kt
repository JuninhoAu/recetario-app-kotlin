package com.juni.recetarioapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.juni.recetarioapp.data.network.RecipeResponse

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel,
    modifier: Modifier = Modifier,
    returnRecipeItem: (RecipeResponse) -> Unit
) {
    val recipeList by viewModel.recipeList.observeAsState(RecipeListState.Idle)
    LaunchedEffect(Unit) {
        viewModel.showRecipeList()
    }
    Box(modifier = modifier.fillMaxSize()) {
        EvaluateStateList(listState = recipeList, returnRecipeItem = returnRecipeItem)
    }
}

@Composable
private fun EvaluateStateList(
    listState: RecipeListState,
    returnRecipeItem: (RecipeResponse) -> Unit
) {
    when (listState) {

        is RecipeListState.Idle -> Unit

        RecipeListState.Loading -> {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is RecipeListState.Success -> {
            RecipeListLazyColumn(recipeList = listState.recipe, returnRecipeItem)
        }

        is RecipeListState.Error -> {
            Text(text = listState.error.message)
        }

    }
}

@Composable
private fun RecipeListLazyColumn(
    recipeList: List<RecipeResponse>,
    returnRecipeItem: (RecipeResponse) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(recipeList) { recipe ->
            CardRecipeItem(recipe = recipe, returnRecipeItem)
        }
    }
}

@Composable
private fun CardRecipeItem(recipe: RecipeResponse, returnRecipeItem: (RecipeResponse) -> Unit) {
    var isRecipeItemFavorite by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                returnRecipeItem(recipe)
            }) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Icon(imageVector = Icons.Default.AccountBox, contentDescription = "image recipe")
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = recipe.nombre, modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = if (isRecipeItemFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                        tint = if (isRecipeItemFavorite) Color.Red else Color.Gray,
                        contentDescription = if (isRecipeItemFavorite) "favorite" else "no favorite",
                        modifier = Modifier.clickable {
                            isRecipeItemFavorite = !isRecipeItemFavorite
                        }
                    )
                }
                Text(text = recipe.descripcion)
            }
        }
    }
}