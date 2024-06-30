package com.example.cinenest.di

import com.example.cinenest.domain.repository.CategoriesRepository
import com.example.cinenest.domain.repository.RecipesDetailsRepository
import com.example.cinenest.domain.repository.RecipesRepository
import com.example.cinenest.domain.repository.SavedRecipesRepository
import com.example.cinenest.domain.repository.UserRepository

interface AppContainer {
    val userRepository: UserRepository
    val recipesRepository: RecipesRepository
    val recipesDetailsRepository: RecipesDetailsRepository
    val categoriesRepository: CategoriesRepository
    val savedRecipesRepository: SavedRecipesRepository
}