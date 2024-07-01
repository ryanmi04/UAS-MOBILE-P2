package com.example.dishdiary.data.repository

import com.example.dishdiary.data.network.MealApi
import com.example.dishdiary.data.network.model.Recipe
import com.example.dishdiary.domain.repository.RecipesRepository
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val apiService: MealApi
) : RecipesRepository {
    override suspend fun getRecipesByCategory(category: String): List<Recipe> {
        val recipes = apiService.getRecipesByCategory(category).meals
        recipes.forEach {
            it.strCategory = category
        }
        return recipes
    }
}