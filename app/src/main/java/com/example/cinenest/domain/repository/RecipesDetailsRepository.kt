package com.example.cinenest.domain.repository

import com.example.cinenest.data.network.model.RecipeDetails

interface RecipesDetailsRepository {

    suspend fun getRecipeDetailsById(id: String): RecipeDetails

    suspend fun getRandomRecipe(): RecipeDetails

    suspend fun searchRecipes(query: String): List<RecipeDetails>
}