package com.example.dishdiary.data.repository

import com.example.dishdiary.data.local.MealDao
import com.example.dishdiary.data.network.model.RecipeDetails
import com.example.dishdiary.domain.repository.SavedRecipesRepository

class FavoriteRecipesRepositoryImpl(private val mealDao: MealDao) : SavedRecipesRepository {

    override suspend fun getSavedRecipes(): List<RecipeDetails> {
        return mealDao.getFavoriteRecipes()
    }

    override suspend fun insertRecipe(recipeDetails: RecipeDetails) {
        mealDao.insertFavoriteRecipe(recipeDetails)
    }


    override suspend fun getRecipe(idMeal: String): RecipeDetails {
        return mealDao.getRecipe(idMeal)
    }


    override suspend fun deleteRecipe(recipeDetails: RecipeDetails) {
        mealDao.deleteFavoriteRecipe(recipeDetails.idMeal)
    }
}