package com.example.cinenest.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipesResponse(
    val meals: List<Recipe> = listOf()
)