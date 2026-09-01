package com.juni.recetarioapp.view.recipeitemdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.juni.recetarioapp.R
import com.juni.recetarioapp.view.model.RecipeModel

@Composable
fun RecipeItemDetailScreen(modifier: Modifier, recipe: RecipeModel) {

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ShowImage(recipe.imagen)
            Column(modifier = Modifier.padding(12.dp)) {
                ShowTitle(title = recipe.nombre)
                Spacer(modifier = Modifier.size(4.dp))
                ShowDescription(description = recipe.descripcion)
                Spacer(modifier = Modifier.size(12.dp))
                ShowDetails()
                Spacer(modifier = Modifier.size(8.dp))
                ShowIngredientsList(ingredients = recipe.ingredientes, steps = recipe.pasos)
            }
        }
    }
}

@Composable
private fun ShowTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}

@Composable
private fun ShowDescription(description: String) {
    Text(
        text = description,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ShowDetails() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Preparacion",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(text = "30 min", fontSize = 12.sp)
            }
            VerticalDivider(
                modifier = Modifier.height(30.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Calorias",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(text = "450 kg", fontSize = 12.sp)
            }
            VerticalDivider(
                modifier = Modifier.height(30.dp),
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Dificultad",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(text = "media", fontSize = 12.sp)
            }
        }
    }
}

@Composable
private fun ShowSubTitle(
    subTitle: String,
    icon: ImageVector,
    contentDescription: String = "",
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onExpandedChange(!expanded) }
            .fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp),
        ) {
            Icon(imageVector = icon, contentDescription = contentDescription)
            Text(
                text = subTitle,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            Icon(
                imageVector = if (!expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                contentDescription = ""
            )
        }
    }
}

@Composable
private fun ShowIngredientsList(ingredients: List<String>, steps: List<String>) {
    var ingredientsExpanded by remember { mutableStateOf(false) }
    var stepsExpanded by remember { mutableStateOf(false) }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            ShowSubTitle(
                subTitle = "Ingredientes",
                Icons.Default.ShoppingCart,
                "",
                ingredientsExpanded,
                onExpandedChange = { ingredientsExpanded = it }
            )
        }
        if (ingredientsExpanded) {
            items(ingredients) { ingredientes ->
                Text(
                    text = "• $ingredientes",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }

        item {
            ShowSubTitle(
                subTitle = "Preparación",
                icon = Icons.Default.CheckCircle,
                "",
                stepsExpanded,
                onExpandedChange = { stepsExpanded = it }
            )
        }
        if (stepsExpanded) {
            items(steps) { pasos ->
                Text(
                    text = "• $pasos",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}

@Composable
private fun ShowImage(imageUrl: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_launcher_background),
            error = painterResource(R.drawable.plato_s1),
            contentDescription = "Image detail"
        )

        IconButton(
            onClick = {}, modifier = Modifier
                .align(Alignment.TopStart)
                .padding(8.dp)
                .size(44.dp)
                .background(color = Color.Black.copy(alpha = 0.45f), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = ""
            )
        }
        IconButton(
            onClick = {}, modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(44.dp)
                .background(color = Color.Black.copy(alpha = 0.45f), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                tint = Color.White,
                contentDescription = ""
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RecipeItemDetailPreviewScreen() {
    val recipe = RecipeModel(
        id = "id",
        nombre = "Arroz con pato",
        imagen = "R.drawable.plato_s1",
        descripcion = "Arroz verde y aromatico acompañado de tierno pato, con un delicioso toque de culantro y especias",
        ingredientes = listOf("arroz", "pato", "ocopa", "zanahoria", "papa"),
        pasos = listOf(
            "corta el pato en presas y sazonalo con sal, primienta, cominio y un poco de ajo molido. Dejalo reporsas unos minutos",
            "corta el pato en presas y sazonalo con sal, primienta, cominio y un poco de ajo molido. Dejalo reporsas unos minutos"
        ),
        favorito = true,
    )
    RecipeItemDetailScreen(modifier = Modifier, recipe = recipe)
}