package com.juni.recetarioapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.juni.recetarioapp.ui.theme.RecetarioAppTheme
import com.juni.recetarioapp.view.OnboardingViewModel
import com.juni.recetarioapp.view.navigation.RecipeListNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: OnboardingViewModel by viewModels()
        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoadingSplash.value
        }
        setContent {
            RecetarioAppTheme {
                RecipeListNav(onboardingViewModel = viewModel)
            }
        }
    }
}
