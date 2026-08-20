package com.juni.recetarioapp.view.onboarding

import android.content.res.Configuration
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
import androidx.compose.material3.Scaffold
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
import com.juni.recetarioapp.view.model.OnboardingModel


@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onFinishOnboarding: () -> Unit) {

    val onboardingPagesLists = listOf(
        OnboardingModel("Buscar recetas", "Descubre miles de recetas al instante."),
        OnboardingModel("Marcar favoritas", "Guarda las recetas que más te gusten."),
        OnboardingModel("Cocina fácil", "Sigue los pasos y cocina como un chef.")
    )
    var pageNumber by remember { mutableIntStateOf(0) }

    val onboardingPage = onboardingPagesLists[pageNumber]

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            ShowOnboardingText(onboardingModel = onboardingPage)
            Spacer(modifier = Modifier.weight(1f))
            ShowIndicatorPage(onboardingPagesList = onboardingPagesLists, pageNumber = pageNumber)
            Spacer(modifier = Modifier.padding(8.dp))
            ShowNextButton(
                onboardingPagesList = onboardingPagesLists,
                pageNumber = pageNumber,
                updatePageNumber = { pageNumber = it },
                onFinishOnboarding = onFinishOnboarding
            )

        }
    }
}

@Composable
private fun ShowOnboardingText(onboardingModel: OnboardingModel) {
    Column {
        Text(text = onboardingModel.onboardingTitle)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = onboardingModel.onboardingDes)
    }
}

@Composable
private fun ShowIndicatorPage(onboardingPagesList: List<OnboardingModel>, pageNumber: Int) {

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
    onboardingPagesList: List<OnboardingModel>,
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

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingScreenPreview() {
    Scaffold { innerPadding ->
        OnboardingScreen(
            modifier = Modifier.padding(innerPadding),
            onFinishOnboarding = {}
        )
    }

}