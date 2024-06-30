package com.example.cinenest.data.repository

import com.example.cinenest.data.network.MealApi
import com.example.cinenest.data.network.model.Recipe
import com.example.cinenest.domain.repository.RecipesRepository
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