package com.example.dishdiary.presentation.screen.favorite

import androidx.compose.runtime.Composable
import com.example.dishdiary.data.network.model.Category
import com.example.dishdiary.data.network.model.Recipe

@Composable
fun getListsFavorites(
    recipes: List<Recipe>,
    categories: List<Category>,
): List<List<Recipe>> {
    var temp: List<Recipe>
    val recipesLists: MutableList<List<Recipe>> = mutableListOf()
    categories.forEach { category ->
        temp = recipes.filter { recipe ->
            recipe.strCategory == category.strCategory
        }
        if(temp.isNotEmpty()) {
            recipesLists.add(temp)
        }
    }
    return recipesLists
}