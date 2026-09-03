package com.juni.recetarioapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.juni.recetarioapp.ui.theme.RecetarioAppTheme
import com.juni.recetarioapp.view.onboarding.OnboardingViewModel
import com.juni.recetarioapp.view.navigation.RecipeListNav
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                android.graphics.Color.TRANSPARENT,
                android.graphics.Color.TRANSPARENT
            )
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        super.onCreate(savedInstanceState)
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
