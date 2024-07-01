package com.example.dishdiary.presentation.screen.searchscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dishdiary.data.network.model.RecipeDetails

@Composable
fun RecipeSearcItem(
    recipes: List<RecipeDetails>,
    modifier: Modifier = Modifier,
    onRecipeListItemClicked: (RecipeDetails) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        items(recipes) { recipe ->
            RecipeCard(
                recipe = recipe,
                onRecipeListItemClicked = onRecipeListItemClicked
            )
        }
    }
}
