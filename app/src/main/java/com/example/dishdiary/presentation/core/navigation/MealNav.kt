package com.example.dishdiary.presentation.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.dishdiary.R

sealed class MealNav(
    val route: String,
    val iconFilled: ImageVector,
    val iconOutlined: ImageVector,
    @StringRes val resourceId: Int
) {
    data object Home : MealNav("home", Icons.Default.Home, Icons.Outlined.Home, R.string.home)
    data object Favorites : MealNav("favorites", Icons.Default.Favorite, Icons.Outlined.FavoriteBorder, R.string.favorites)
    data object Search : MealNav("search", Icons.Default.Search, Icons.Outlined.Search, R.string.search)
    data object RecipeDetail : MealNav("recipeDetail", Icons.Default.MoreVert, Icons.Outlined.MoreVert, R.string.recipe_detail)
}
