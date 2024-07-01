package com.example.dishdiary.viewmodel
import com.example.dishdiary.data.network.model.Recipe

sealed interface RecipeListUiState {
    data object Loading : RecipeListUiState
    data class Success(val recipes: List<Recipe>) : RecipeListUiState
    data object Error : RecipeListUiState
}