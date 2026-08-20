package com.juni.recetarioapp.view.navigation

import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.juni.recetarioapp.view.onboarding.OnboardingScreen
import com.juni.recetarioapp.view.onboarding.OnboardingViewModel
import com.juni.recetarioapp.view.recipeitemdetail.RecipeItemDetailScreen
import com.juni.recetarioapp.view.recipeitemlist.RecipeListScreen
import com.juni.recetarioapp.view.recipeitemlist.RecipeListViewModel
import com.juni.recetarioapp.view.model.RecipeModel

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
        composable(route = RecipeScreenRoute.Onboarding.route) {
            Scaffold { innerPadding ->
                OnboardingScreen(modifier = Modifier.padding(innerPadding)) {
                    navHostController.navigate(RecipeScreenRoute.List.route) {
                        onboardingViewModel.completeOnboarding()
                        popUpTo(route = RecipeScreenRoute.Onboarding.route) { inclusive = true }
                    }
                }
            }
        }
        composable(route = RecipeScreenRoute.List.route) { backStackEntry ->
            val viewModel: RecipeListViewModel = hiltViewModel()
            Scaffold { innerPadding ->
                RecipeListScreen(
                    viewModel = viewModel,
                    modifier = Modifier.padding(innerPadding),
                    returnRecipeItem = { recipeItem ->
                        val encodedItem = Uri.encode(Gson().toJson(recipeItem))
                        navHostController.navigate(RecipeScreenRoute.Detail.createRoute(encodedItem))
                    })
            }
        }
        composable(
            route = RecipeScreenRoute.Detail.route,
            arguments = listOf(navArgument("encodedItem") {
                type = NavType.StringType
                defaultValue = ""
            })
        ) { backStackEntry ->
            val item = backStackEntry.arguments?.getString("encodedItem").orEmpty()
            val recipe =
                runCatching { Gson().fromJson(item, RecipeModel::class.java) }.getOrNull()
            Scaffold { innerPadding ->
                recipe?.let { model ->
                    RecipeItemDetailScreen(
                        modifier = Modifier.padding(innerPadding),
                        recipe = model
                    )
                }
            }
        }
    }
}