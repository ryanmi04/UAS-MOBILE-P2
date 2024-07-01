package com.example.dishdiary.presentation.screen.recipedetail

import com.example.dishdiary.viewmodel.MealViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dishdiary.R
import com.example.dishdiary.viewmodel.RecipeDetailsUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    mealViewModel: MealViewModel,
    recipeName: String,
    navigateUp: () -> Unit,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        when (val recipeDetailsUiState = mealViewModel.recipeDetailsUiState) {
            is RecipeDetailsUiState.Success -> {
                val recipeDetails = recipeDetailsUiState.recipeDetails
                TopAppBar(
                    title = { Text(text = recipeName) },
                    navigationIcon = {
                        IconButton(onClick = navigateUp) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    },
                    actions = {
                        if (!recipeDetailsUiState.isFavorite) {
                            IconButton(
                                onClick = { mealViewModel.saveRecipe(recipeDetails) }
                            ) {
                                Icon(
                                    Icons.Filled.FavoriteBorder,
                                    contentDescription = null
                                )
                            }
                        } else {
                            IconButton(
                                onClick = { mealViewModel.deleteRecipe(recipeDetails) }
                            ) {
                                Icon(
                                    Icons.Filled.Favorite,
                                    contentDescription = null
                                )
                            }
                        }
                    }
                )

                LazyColumn(
                    contentPadding = PaddingValues(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(32.dp),
                ) {
                    item {
                        Box {
                            AsyncImage(
                                model = recipeDetails.strMealThumb,
                                contentDescription = recipeDetails.strMeal,
                                contentScale = ContentScale.Crop,
                                modifier =
                                if(windowSize != WindowWidthSizeClass.Expanded) {
                                    Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(4 / 3f)
                                } else {
                                    Modifier
                                        .fillMaxWidth(0.5f)
                                        .aspectRatio(4 / 3f)
                                }
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center
                        ) {
                            if (recipeDetails.strArea != "" && recipeDetails.strTags != "") {
                                Text(
                                    text = "${recipeDetails.strArea} | ${recipeDetails.strTags}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(16.dp)
                                )
                            } else {
                                Text(
                                    text = "${recipeDetails.strArea}${recipeDetails.strTags}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }

                    }

                    item {
                        Text(
                            text = stringResource(R.string.ingredients),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        val nbChunks = if(windowSize != WindowWidthSizeClass.Expanded) 3 else 6
                        IngredientsGrid(recipe = recipeDetails, nbChunks = nbChunks)
                    }

                    item {
                        Text(
                            text = "Instructions",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(
                                text = recipeDetails.strInstructions,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(8.dp),
                            )
                        }
                    }
                }
            }

            RecipeDetailsUiState.Loading -> {
                TopAppBar(
                    title = { Text(text = recipeName) },
                    navigationIcon = {
                        IconButton(onClick = navigateUp) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    }
                )
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(16.dp)
                )
            }

            RecipeDetailsUiState.Error -> {
                TopAppBar(
                    title = { Text(text = recipeName) },
                    navigationIcon = {
                        IconButton(onClick = navigateUp) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    }
                )
            }
        }
    }
}