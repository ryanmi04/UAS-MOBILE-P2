package com.example.dishdiary.domain.repository

import com.example.dishdiary.data.network.model.RecipeDetails

interface RecipesDetailsRepository {

    suspend fun getRecipeDetailsById(id: String): RecipeDetails

    suspend fun getRandomRecipe(): RecipeDetails

    suspend fun searchRecipes(query: String): List<RecipeDetails>
}