package com.example.dishdiary.data.repository

import com.example.dishdiary.data.network.MealApi
import com.example.dishdiary.data.network.model.RecipeDetails
import com.example.dishdiary.domain.repository.RecipesDetailsRepository
import javax.inject.Inject

class RecipesDetailsRepositoryImpl @Inject constructor(
    private val apiService: MealApi
) : RecipesDetailsRepository {

    override suspend fun getRecipeDetailsById(id: String): RecipeDetails {
        return apiService.getRecipeDetailsById(id).meals.first()
    }

    override suspend fun getRandomRecipe(): RecipeDetails {
        return apiService.getRandomRecipe().meals.first()
    }

    override suspend fun searchRecipes(query: String): List<RecipeDetails> {
        return apiService.searchRecipes(query).meals
    }
}
