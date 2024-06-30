package com.example.cinenest.viewmodel

import com.example.cinenest.data.network.model.RecipeDetails

sealed interface RecipeDetailsUiState {
    data object Loading : RecipeDetailsUiState
    data class Success(
        val recipeDetails: RecipeDetails,
        val isFavorite: Boolean
    ) : RecipeDetailsUiState
    data object Error : RecipeDetailsUiState
}