package com.juni.recetarioapp.view.recipeitemlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.juni.recetarioapp.R
import com.juni.recetarioapp.view.model.RecipeModel


@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel,
    modifier: Modifier = Modifier,
    returnRecipeItem: (RecipeModel) -> Unit
) {
    val recipeList by viewModel.getRecipeList.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.showRecipeList()
    }
    Box(modifier = modifier.fillMaxSize()) {
        EvaluateStateList(
            listState = recipeList,
            selectRecipeItem = { item -> returnRecipeItem(item) },
            favoriteRecipeItem = { favorite -> viewModel.addRecipeItemToFav(favorite) })
    }
}

@Composable
private fun EvaluateStateList(
    listState: RecipeListState,
    selectRecipeItem: (RecipeModel) -> Unit,
    favoriteRecipeItem: (RecipeModel) -> Unit
) {
    when (listState) {

        is RecipeListState.Idle -> Unit

        RecipeListState.Loading -> {

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is RecipeListState.Success -> {
            RecipeListLazyColumn(
                recipeList = listState.recipeList,
                onItemClick = selectRecipeItem,
                onItemFavorite = favoriteRecipeItem
            )
        }

        is RecipeListState.Error -> {
            Text(text = listState.error.message)
        }

    }
}

@Composable
private fun RecipeListLazyColumn(
    recipeList: List<RecipeModel>,
    onItemClick: (RecipeModel) -> Unit,
    onItemFavorite: (RecipeModel) -> Unit
) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "Recetas",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Comidas saludables para tu dia a dia",
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        )
        Spacer(modifier = Modifier.size(8.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(recipeList) { recipe ->
                CardRecipeItem(
                    recipeName = recipe.nombre,
                    recipeDescription = recipe.descripcion,
                    isFavoriteRecipe = recipe.favorito,
                    imageUrl = recipe.imagen,
                    onFavClick = { onItemFavorite(recipe) }
                ) {
                    onItemClick(recipe)
                }
            }
        }
    }

}

@Composable
private fun CardRecipeItem(
    recipeName: String,
    recipeDescription: String,
    isFavoriteRecipe: Boolean,
    imageUrl: String,
    onFavClick: () -> Unit,
    onCardClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onCardClick()
            }, colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ShowImage(imageUrl)
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = recipeName,
                        modifier = Modifier.weight(1f),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = if (isFavoriteRecipe) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                        tint = if (isFavoriteRecipe) Color.Red else Color.Gray,
                        contentDescription = if (isFavoriteRecipe) "favorite" else "no favorite",
                        modifier = Modifier
                            .clickable {
                                onFavClick()
                            }
                            .background(Color.Green.copy(0.45f), shape = CircleShape)
                            .padding(8.dp)
                            .size(16.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        tint = Color(0XFF4CAF20),
                        contentDescription = "",
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "30 minutos",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Light
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = recipeDescription,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
private fun ShowImage(imageUrl: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true)
            .build(),
        modifier = Modifier
            .width(54.dp)
            .height(54.dp),
        contentScale = ContentScale.Crop,
        placeholder = painterResource(R.drawable.ic_launcher_background),
        error = painterResource(R.drawable.onboarding_icon1),
        contentDescription = "Image item list"
    )

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RecipeListPreviewScreen() {
    EvaluateStateList(
        listState = RecipeListState.Success(
            List(3) {
                RecipeModel(
                    id = "12",
                    nombre = "Papa a la huancaina",
                    imagen = "R.drawable.plato_s1",
                    descripcion = "Plato tipico de huancayo",
                    ingredientes = listOf("ola", "todo bien", "ayuda"),
                    pasos = listOf("hola", "como estas", "todo bien"),
                    favorito = false
                )
            }
        ),
        selectRecipeItem = {},
        favoriteRecipeItem = {},
    )
}