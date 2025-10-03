package com.juni.recetarioapp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.juni.recetarioapp.view.model.OnboardingPage


@Composable
fun OnboardingScreen(onFinishOnboarding: () -> Unit) {

    val onboardingPagesList = listOf(
        OnboardingPage("Buscar recetas", "Descubre miles de recetas al instante."),
        OnboardingPage("Marcar favoritas", "Guarda las recetas que más te gusten."),
        OnboardingPage("Cocina fácil", "Sigue los pasos y cocina como un chef.")
    )
    var pageNumber by remember { mutableIntStateOf(0) }

    val onboardingPage = onboardingPagesList[pageNumber]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        ShowOnboardingText(onboardingPage = onboardingPage)
        Spacer(modifier = Modifier.weight(1f))
        ShowIndicatorPage(onboardingPagesList = onboardingPagesList, pageNumber = pageNumber)
        Spacer(modifier = Modifier.padding(8.dp))
        ShowNextButton(
            onboardingPagesList = onboardingPagesList,
            pageNumber = pageNumber,
            updatePageNumber = { pageNumber = it },
            onFinishOnboarding = onFinishOnboarding
        )

    }
}

@Composable
private fun ShowOnboardingText(onboardingPage: OnboardingPage) {
    Column {
        Text(text = onboardingPage.onboardingTitle)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = onboardingPage.onboardingDes)
    }
}

@Composable
private fun ShowIndicatorPage(onboardingPagesList: List<OnboardingPage>, pageNumber: Int) {

    Row {
        onboardingPagesList.forEachIndexed { index, _ ->
            val color = if (index == pageNumber) Color.Black else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color)

            )
        }
    }
}

@Composable
private fun ShowNextButton(
    onboardingPagesList: List<OnboardingPage>,
    pageNumber: Int,
    updatePageNumber: (Int) -> Unit,
    onFinishOnboarding: () -> Unit
) {
    Button(
        onClick = {
            if (pageNumber < onboardingPagesList.lastIndex) {
                updatePageNumber(pageNumber + 1)
            } else {
                onFinishOnboarding()
            }
        }
    ) {
        Text(if (pageNumber < onboardingPagesList.lastIndex) "Siguiente" else "Empezar")
    }
}

@Preview(showBackground = true)
@Composable
private fun OnboardingScreenPreview() {
    OnboardingScreen(
        onFinishOnboarding = {}
    )
}