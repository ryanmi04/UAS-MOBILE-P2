package com.example.dishdiary.data.repository

import com.example.dishdiary.data.network.MealApi
import com.example.dishdiary.data.network.model.Category
import com.example.dishdiary.domain.repository.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: MealApi
) : CategoriesRepository {
    override suspend fun getCategories(): List<Category> {
        return apiService.getCategories().categories
    }
}