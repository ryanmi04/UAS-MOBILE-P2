package com.example.cinenest.domain.repository

import com.example.cinenest.data.network.model.Recipe

interface RecipesRepository {
    suspend fun getRecipesByCategory(category: String): List<Recipe>
}
