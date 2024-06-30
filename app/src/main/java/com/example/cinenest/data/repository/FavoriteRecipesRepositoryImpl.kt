package com.example.cinenest.data.repository

import com.example.cinenest.data.local.MealDao
import com.example.cinenest.data.network.model.RecipeDetails
import com.example.cinenest.domain.repository.SavedRecipesRepository

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