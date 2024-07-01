package com.example.dishdiary.presentation.screen.homescreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.dishdiary.data.network.model.Recipe

@Composable
fun RecipeItem(
    recipe: Recipe,
    onRecipeListItemClicked: (Recipe) -> Unit
) {
    Card(
        modifier = Modifier.width(200.dp),
        onClick = {
            onRecipeListItemClicked(recipe)
        }
    ) {
        Column {
            Box {
                AsyncImage(
                    model = recipe.strMealThumb,
                    contentDescription = recipe.strMeal,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Spacer(modifier = Modifier.size(4.dp))
                Row {
                    Text(
                        text = recipe.strMeal,
                        style = MaterialTheme.typography.labelLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2,
                        minLines = 2
                    )

                }
            }
        }
    }
}