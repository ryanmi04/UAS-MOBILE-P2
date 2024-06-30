package com.example.cinenest.viewmodel

import com.example.cinenest.data.network.model.RecipeDetails

sealed interface SearchUiState {
    data object Empty : SearchUiState
    data object Loading : SearchUiState
    data class Success(val recipes: List<RecipeDetails>) : SearchUiState
    data object Error : SearchUiState
}