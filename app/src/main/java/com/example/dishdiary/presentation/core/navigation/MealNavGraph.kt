package com.example.dishdiary.presentation.core.navigation

import com.example.dishdiary.viewmodel.MealViewModel
import com.example.dishdiary.presentation.screen.recipedetail.RecipeDetailScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dishdiary.presentation.screen.HomeScreen
import com.example.dishdiary.presentation.screen.favorite.FavoritesScreen
import com.example.dishdiary.presentation.screen.searchscreen.SearchScreen

@Composable
fun MealNavGraph(
    mealViewModel: MealViewModel,
    navController: NavHostController,
    innerPadding: PaddingValues,
    windowSize: WindowWidthSizeClass
) {
    NavHost(
        navController = navController,
        startDestination = MealNav.Home.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(MealNav.Home.route) {
            HomeScreen(
                recipeListUiState = mealViewModel.recipeListUiState,
                categoriesFlow = mealViewModel.categories,
                onRecipeListItemClicked = {
                    mealViewModel.selectedRecipeName = it.strMeal
                    mealViewModel.getRecipeDetailsById(it.idMeal)
                    navController.navigate(MealNav.RecipeDetail.route)
                }
            )
        }
        composable(MealNav.Search.route) {
            SearchScreen(
                viewModel = mealViewModel,
                onRecipeListItemClicked = {
                    mealViewModel.getRecipeDetailsByObject(it)
                    navController.navigate(MealNav.RecipeDetail.route)
                },
            )
        }
        composable(MealNav.Favorites.route) {
            FavoritesScreen(
                favoriteRecipesListUiState = mealViewModel.favoriteRecipesListUiState,
                categoriesFlow = mealViewModel.categories,
                onRecipeListItemClicked = {
                    mealViewModel.selectedRecipeName = it.strMeal
                    mealViewModel.getRecipeDetailsById(it.idMeal)
                    navController.navigate(MealNav.RecipeDetail.route)
                }
            )
        }
        composable(MealNav.RecipeDetail.route) {
            RecipeDetailScreen(
                mealViewModel = mealViewModel,
                recipeName = mealViewModel.selectedRecipeName,
                navigateUp = { navController.navigateUp() },
                windowSize = windowSize
            )
        }
    }
}
