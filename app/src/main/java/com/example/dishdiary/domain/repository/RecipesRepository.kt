package com.example.dishdiary.domain.repository

import com.example.dishdiary.data.network.model.Recipe

interface RecipesRepository {
    suspend fun getRecipesByCategory(category: String): List<Recipe>
}
