package com.juni.recetarioapp.view.onboarding

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.juni.recetarioapp.R
import com.juni.recetarioapp.utils.onboarding.onboardingPageData
import com.juni.recetarioapp.view.model.OnboardingModel
import kotlinx.coroutines.launch


@Composable
fun OnboardingScreen(onFinishOnboarding: () -> Unit) {

    val onboardingPagesLists = remember { onboardingPageData }
    val pagerState = rememberPagerState(pageCount = { onboardingPagesLists.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(R.drawable.fondo1),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Spacer(modifier = Modifier.weight(1f))

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(6f)
            ) { position ->
                OnboardingContent(model = onboardingPagesLists[position])
            }
            Spacer(modifier = Modifier.height(12.dp))
            PageIndicator(
                size = onboardingPagesLists.size,
                currentPage = pagerState.currentPage
            )
            Spacer(modifier = Modifier.weight(1f))

            ShowNextButton(
                isLastPage = pagerState.currentPage == onboardingPagesLists.size - 1,
                onNextCLick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                },
                onFinish = onFinishOnboarding
            )
        }
    }
}

@Composable
private fun OnboardingContent(model: OnboardingModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(model.onboardingIcon),
            contentDescription = "",
            modifier = Modifier.size(240.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = model.onboardingTitle,
            fontSize = 26.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            lineHeight = 34.sp,
            modifier = Modifier.padding(horizontal = 14.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = model.onboardingDes,
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 14.dp)
        )
    }
}

@Composable
private fun PageIndicator(size: Int, currentPage: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        repeat(size) { index ->
            val isSelected = index == currentPage
            val color = if (isSelected) Color(0xFF4CAF20) else Color.LightGray
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(if (isSelected) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .background(color)
            )
        }
    }
}

@Composable
private fun ShowNextButton(isLastPage: Boolean, onNextCLick: () -> Unit, onFinish: () -> Unit) {
    Button(
        modifier = Modifier.height(46.dp), colors = ButtonColors(
            containerColor = Color(0xFF4CAF20),
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.Black
        ),
        onClick = {
            if (isLastPage) {
                onFinish()
            } else {
                onNextCLick()
            }
        }
    ) {
        Text(
            text = if (isLastPage) "Empezar" else "Siguiente",
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun OnboardingScreenPreview() {
    OnboardingScreen(
        onFinishOnboarding = {}
    )
}
