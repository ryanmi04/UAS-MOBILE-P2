package com.example.cinenest.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cinenest.data.network.model.Category
import com.example.cinenest.data.network.model.Recipe
import com.example.cinenest.presentation.component.RecipeItem
import com.example.cinenest.presentation.component.RecipeList
import com.example.cinenest.viewmodel.RecipeListUiState
import kotlinx.coroutines.flow.Flow

@Composable
fun HomeScreen(
    recipeListUiState: RecipeListUiState,
    categoriesFlow: Flow<List<Category>>,
    modifier: Modifier = Modifier,
    onRecipeListItemClicked: (Recipe) -> Unit
) {
    val categories by categoriesFlow.collectAsState(initial = emptyList())
    var selectedCategory by rememberSaveable { mutableStateOf(categories.firstOrNull()?.strCategory ?: "") }

    LaunchedEffect(Unit) {
        if (selectedCategory.isEmpty()) {
            selectedCategory = categories.firstOrNull { it.strCategory == "Beef" }?.strCategory ?: categories.firstOrNull()?.strCategory ?: ""
        }
    }

    when (recipeListUiState) {
        is RecipeListUiState.Success -> {
            val recipes = recipeListUiState.recipes
            Column {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        Button(
                            onClick = { selectedCategory = category.strCategory },
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Text(text = category.strCategory)
                        }
                    }
                }
                Column (modifier = Modifier.padding(horizontal = 16.dp)){
                    Text(
                        text = selectedCategory,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(vertical = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(recipes.filter { it.strCategory == selectedCategory }) { recipe ->
                            RecipeItem(
                                recipe = recipe,
                                onRecipeListItemClicked = onRecipeListItemClicked
                            )
                        }
                    }
                }
            }
        }
        RecipeListUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Text(
                        text = "Loading...",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        is RecipeListUiState.Error -> {
            Text(
                text = "Error: Something went wrong!",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
