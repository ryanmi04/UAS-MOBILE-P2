package com.example.dishdiary.domain.repository

import com.example.dishdiary.data.network.model.RecipeDetails

interface SavedRecipesRepository {

    suspend fun getSavedRecipes(): List<RecipeDetails>
    suspend fun insertRecipe(recipeDetails: RecipeDetails)


    suspend fun getRecipe(idMeal: String): RecipeDetails

    suspend fun deleteRecipe(recipeDetails: RecipeDetails)
}