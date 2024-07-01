package com.example.dishdiary.data.network

import com.example.dishdiary.data.network.model.CategoriesResponse
import com.example.dishdiary.data.network.model.RecipeDetailsResponse
import com.example.dishdiary.data.network.model.RecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {

    @GET("filter.php")
    suspend fun getRecipesByCategory(
        @Query("c")
        category: String
    ): RecipesResponse


    @GET("random.php")
    suspend fun getRandomRecipe(): RecipeDetailsResponse


    @GET("lookup.php")
    suspend fun getRecipeDetailsById(
        @Query("i")
        id: String
    ): RecipeDetailsResponse


    @GET("categories.php")
    suspend fun getCategories(): CategoriesResponse


    @GET("search.php")
    suspend fun searchRecipes(
        @Query("s")
        query: String
    ): RecipeDetailsResponse
}