package com.example.dishdiary.presentation.screen.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dishdiary.data.network.model.RecipeDetails

@Composable
fun RecipeCard(
    recipe: RecipeDetails,
    modifier: Modifier = Modifier,
    onRecipeListItemClicked: (RecipeDetails) -> Unit
) {
    Card(
        onClick = { onRecipeListItemClicked(recipe)},
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                AsyncImage(
                    model = recipe.strMealThumb,
                    contentDescription = recipe.strMeal,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Text(
                    text = recipe.strMeal,
                    style = MaterialTheme.typography.displayMedium,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    minLines = 2
                )

            }
        }
    }
}