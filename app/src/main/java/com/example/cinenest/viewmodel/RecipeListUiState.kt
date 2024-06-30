package com.example.cinenest.viewmodel
import com.example.cinenest.data.network.model.Recipe

sealed interface RecipeListUiState {
    data object Loading : RecipeListUiState
    data class Success(val recipes: List<Recipe>) : RecipeListUiState
    data object Error : RecipeListUiState
}