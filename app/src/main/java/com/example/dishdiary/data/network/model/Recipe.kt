package com.example.dishdiary.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Recipe(
    val idMeal: String = "",
    val strMeal: String = "",
    val strMealThumb: String = "",
    var strCategory: String = "",
)