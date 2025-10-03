package com.juni.recetarioapp.view.navigation

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.juni.recetarioapp.data.network.RecipeResponse
import com.juni.recetarioapp.view.OnboardingScreen
import com.juni.recetarioapp.view.OnboardingViewModel
import com.juni.recetarioapp.view.RecipeItemDetailScreen
import com.juni.recetarioapp.view.RecipeListScreen
import com.juni.recetarioapp.view.RecipeListViewModel

@Composable
fun RecipeListNav(
    onboardingViewModel: OnboardingViewModel,
    navHostController: NavHostController = rememberNavController()
) {
    if (onboardingViewModel.isLoadingSplash.value) {
        return
    }
    val starNavigation =
        if (onboardingViewModel.shouldShowOnboarding.value) "onboarding" else "recipeListScreen"
    NavHost(
        navController = navHostController,
        startDestination = starNavigation
    ) {
        composable(route = "onboarding") {
            OnboardingScreen {
                navHostController.navigate("recipeListScreen") {
                    onboardingViewModel.completeOnboarding()
                    popUpTo("onboarding") { inclusive = true }
                }
            }
        }
        composable(route = "recipeListScreen") { backStackEntry ->
            val viewModel: RecipeListViewModel = hiltViewModel()
            Scaffold { innerPadding ->
                RecipeListScreen(
                    viewModel = viewModel,
                    modifier = Modifier.padding(innerPadding),
                    returnRecipeItem = { recipeItem ->
                        val encodedItem = Uri.encode(Gson().toJson(recipeItem))
                        navHostController.navigate("recipeItemDetailScreen?item=$encodedItem")
                    })
            }
        }
        composable(
            "recipeItemDetailScreen?item={encodedItem}",
            arguments = listOf(navArgument("encodedItem") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { backStackEntry ->
            val item = backStackEntry.arguments?.getString("encodedItem").orEmpty()
            val recipe =
                runCatching { Gson().fromJson(item, RecipeResponse::class.java) }.getOrNull()
            Scaffold { innerPadding ->
                recipe?.let {
                    RecipeItemDetailScreen(
                        modifier = Modifier.padding(innerPadding),
                        recipe = it
                    )
                }
            }
        }
    }
}