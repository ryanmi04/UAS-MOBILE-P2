package com.example.dishdiary.presentation.screen.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dishdiary.R
import com.example.dishdiary.data.network.model.Category
import com.example.dishdiary.data.network.model.Recipe
import com.example.dishdiary.viewmodel.FavoriteRecipesListUiState
import kotlinx.coroutines.flow.Flow

@Composable
fun FavoritesScreen(
    favoriteRecipesListUiState: FavoriteRecipesListUiState,
    categoriesFlow: Flow<List<Category>>,
    modifier: Modifier = Modifier,
    onRecipeListItemClicked: (Recipe) -> Unit
) {
    val categories by categoriesFlow.collectAsState(initial = emptyList())
    when (favoriteRecipesListUiState) {
        is FavoriteRecipesListUiState.Success -> {
            val recipes = favoriteRecipesListUiState.recipes
            val favorites: List<List<Recipe>> = getListsFavorites(
                recipes = recipes,
                categories = categories
            )
            if(favorites.isEmpty()) {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(R.string.empty_favorites),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                ) {
                    items(favorites) { recipes ->
                        RecipeList(
                            recipeList = recipes,
                            title = recipes.first().strCategory,
                            modifier = modifier,
                            onRecipeListItemClicked = onRecipeListItemClicked
                        )
                    }
                }
            }
        }
        is FavoriteRecipesListUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator() // Circular indicator
                    Text(
                        text = stringResource(R.string.loading),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        is FavoriteRecipesListUiState.Error -> {
            Text(
                text = stringResource(R.string.error_something_went_wrong),
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}