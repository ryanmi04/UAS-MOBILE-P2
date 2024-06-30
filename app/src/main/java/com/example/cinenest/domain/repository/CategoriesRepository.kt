package com.example.cinenest.domain.repository

import com.example.cinenest.data.network.model.Category

interface CategoriesRepository {
    suspend fun getCategories(): List<Category>
}
