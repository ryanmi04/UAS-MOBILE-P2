package com.example.cinenest.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinenest.data.network.model.Recipe

@Composable
fun RecipeList(
    modifier: Modifier = Modifier,
    title: String = "",
    recipeList: List<Recipe>,
    onRecipeListItemClicked: (Recipe) -> Unit
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.size(12.dp))
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(recipeList) { recipe ->
                RecipeItem(recipe = recipe, onRecipeListItemClicked = onRecipeListItemClicked)
            }
        }
    }
}