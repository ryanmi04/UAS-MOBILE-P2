package com.example.cinenest.data.repository

import com.example.cinenest.data.network.MealApi
import com.example.cinenest.data.network.model.Category
import com.example.cinenest.domain.repository.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: MealApi
) : CategoriesRepository {
    override suspend fun getCategories(): List<Category> {
        return apiService.getCategories().categories
    }
}