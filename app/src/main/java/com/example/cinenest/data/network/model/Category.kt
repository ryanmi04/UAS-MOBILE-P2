package com.example.cinenest.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val strCategory: String = "",
    val strCategoryThumb: String = "",
    val strCategoryDescription: String = ""
)
