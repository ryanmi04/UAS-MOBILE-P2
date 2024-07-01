package com.example.dishdiary.presentation.screen.recipedetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dishdiary.data.network.model.RecipeDetails

@Composable
fun IngredientsGrid(
    recipe: RecipeDetails,
    nbChunks: Int,
) {
    val ingredients = listOf(
        recipe.strIngredient1 to recipe.strMeasure1,
        recipe.strIngredient2 to recipe.strMeasure2,
        recipe.strIngredient3 to recipe.strMeasure3,
        recipe.strIngredient4 to recipe.strMeasure4,
        recipe.strIngredient5 to recipe.strMeasure5,
        recipe.strIngredient6 to recipe.strMeasure6,
        recipe.strIngredient7 to recipe.strMeasure7,
        recipe.strIngredient8 to recipe.strMeasure8,
        recipe.strIngredient9 to recipe.strMeasure9,
        recipe.strIngredient10 to recipe.strMeasure10,
        recipe.strIngredient11 to recipe.strMeasure11,
        recipe.strIngredient12 to recipe.strMeasure12,
        recipe.strIngredient13 to recipe.strMeasure13,
        recipe.strIngredient14 to recipe.strMeasure14,
        recipe.strIngredient15 to recipe.strMeasure15,
        recipe.strIngredient16 to recipe.strMeasure16,
        recipe.strIngredient17 to recipe.strMeasure17,
        recipe.strIngredient18 to recipe.strMeasure18,
        recipe.strIngredient19 to recipe.strMeasure19,
        recipe.strIngredient20 to recipe.strMeasure20
    )

    val gridRows = ingredients.chunked(nbChunks)
    gridRows.forEach { row ->
        val maxWidthFraction = row.size / 3f
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(maxWidthFraction)
        ) {
            row.forEach { (ingredient, measure) ->
                if(ingredient != "") {
                    Box(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                    ) {
                        IngredientCard(ingredient, measure)
                    }
                } else {
                    Box (
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .weight(1f)
                    )
                }
            }
        }
    }
}