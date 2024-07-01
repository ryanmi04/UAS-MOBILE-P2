package com.example.dishdiary.data.network.model


import kotlinx.serialization.Serializable

@Serializable
data class CategoriesResponse(
    val categories: List<Category> = listOf()
)
