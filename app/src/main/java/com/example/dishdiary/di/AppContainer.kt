package com.example.dishdiary.di

import com.example.dishdiary.domain.repository.CategoriesRepository
import com.example.dishdiary.domain.repository.RecipesDetailsRepository
import com.example.dishdiary.domain.repository.RecipesRepository
import com.example.dishdiary.domain.repository.SavedRecipesRepository
import com.example.dishdiary.domain.repository.UserRepository

interface AppContainer {
    val userRepository: UserRepository
    val recipesRepository: RecipesRepository
    val recipesDetailsRepository: RecipesDetailsRepository
    val categoriesRepository: CategoriesRepository
    val savedRecipesRepository: SavedRecipesRepository
}