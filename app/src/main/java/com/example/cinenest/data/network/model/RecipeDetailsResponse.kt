package com.example.cinenest.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeDetailsResponse(
    val meals: List<RecipeDetails> = listOf()
)