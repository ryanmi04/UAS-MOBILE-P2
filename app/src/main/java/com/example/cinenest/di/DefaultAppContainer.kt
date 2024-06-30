package com.example.cinenest.di

import android.content.Context
import com.example.cinenest.data.local.MealDatabase
import com.example.cinenest.data.network.MealApi
import com.example.cinenest.data.repository.*
import com.example.cinenest.domain.repository.*
import com.example.cinenest.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class DefaultAppContainer(private val context: Context) : AppContainer {

    private fun getLoggerInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private val mealJson = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(
            okhttp3.OkHttpClient.Builder()
                .addInterceptor(getLoggerInterceptor())
                .connectTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(20, java.util.concurrent.TimeUnit.SECONDS)
                .build()
        )
        .addConverterFactory(mealJson.asConverterFactory("application/json".toMediaType()))
        .baseUrl(Constants.BASE_URL + Constants.NUMBER + "/")
        .build()

    private val retrofitService: MealApi by lazy {
        retrofit.create(MealApi::class.java)
    }

    override val recipesRepository: RecipesRepository by lazy {
        RecipesRepositoryImpl(retrofitService)
    }

    override val recipesDetailsRepository: RecipesDetailsRepository by lazy {
        RecipesDetailsRepositoryImpl(retrofitService)
    }

    override val categoriesRepository: CategoriesRepository by lazy {
        CategoriesRepositoryImpl(retrofitService)
    }

    override val savedRecipesRepository: SavedRecipesRepository by lazy {
        FavoriteRecipesRepositoryImpl(MealDatabase.getDatabase(context).mealDao())
    }

    override val userRepository: UserRepository by lazy {
        UserRepositoryImpl(MealDatabase.getDatabase(context).userDao())
    }
}
