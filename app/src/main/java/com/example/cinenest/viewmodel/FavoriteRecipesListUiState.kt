package com.example.cinenest.viewmodel

import com.example.cinenest.data.network.model.Recipe

sealed interface FavoriteRecipesListUiState {
    data object Loading : FavoriteRecipesListUiState
    data class Success(val recipes: List<Recipe>) : FavoriteRecipesListUiState
    data object Error : FavoriteRecipesListUiState
}