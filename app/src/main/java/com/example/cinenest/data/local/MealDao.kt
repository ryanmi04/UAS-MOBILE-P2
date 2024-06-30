package com.example.cinenest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinenest.data.network.model.RecipeDetails

@Dao
interface MealDao {
    @Query("SELECT * FROM favorite_recipes")
    suspend fun getFavoriteRecipes(): List<RecipeDetails>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavoriteRecipe(recipeDetails: RecipeDetails)

    @Query("SELECT * FROM favorite_recipes where idMeal = :idMeal")
    suspend fun getRecipe(idMeal: String): RecipeDetails

    @Query("DELETE FROM favorite_recipes where idMeal = :idMeal")
    suspend fun deleteFavoriteRecipe(idMeal: String)
}