package com.juni.recetarioapp.view.recipeitemdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.juni.recetarioapp.view.model.RecipeModel

@Composable
fun RecipeItemDetailScreen(modifier: Modifier, recipe: RecipeModel) {

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Icon(
                imageVector = Icons.Default.AccountBox,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                contentDescription = "image recipe detail"
            )

            Text(
                text = recipe.nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
            )
            Text(
                text = recipe.descripcion,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(recipe.ingredientes) { ingredientes ->
                    Text(text = "-${ingredientes}")
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
                    .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(recipe.pasos) { pasos ->
                    Text(text = "-${pasos}")
                }
            }
        }

    }
}