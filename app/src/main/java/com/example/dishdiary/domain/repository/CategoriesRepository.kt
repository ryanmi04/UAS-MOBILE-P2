package com.example.dishdiary.domain.repository

import com.example.dishdiary.data.network.model.Category

interface CategoriesRepository {
    suspend fun getCategories(): List<Category>
}
