package com.example.dishdiary.viewmodel

import com.example.dishdiary.data.network.model.Recipe

sealed interface FavoriteRecipesListUiState {
    data object Loading : FavoriteRecipesListUiState
    data class Success(val recipes: List<Recipe>) : FavoriteRecipesListUiState
    data object Error : FavoriteRecipesListUiState
}