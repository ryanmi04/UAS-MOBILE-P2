//package com.example.cinenest.di
//
//import com.example.cinenest.data.network.MealApi
//import com.example.cinenest.utils.Constants
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import kotlinx.serialization.json.Json
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Retrofit
//import java.util.concurrent.TimeUnit
//import javax.inject.Singleton
//
//import com.example.cinenest.utils.Constants.BASE_URL
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
//import okhttp3.MediaType.Companion.toMediaType
//
//
//@Module
//@InstallIn(SingletonComponent::class)
//object NetworkModule {
//
//    private val MealJson = Json {
//        ignoreUnknownKeys = true
//        coerceInputValues = true
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//        return OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .connectTimeout(20, TimeUnit.SECONDS)
//            .readTimeout(20, TimeUnit.SECONDS)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideForkTalesJson(): Json {
//        return Json {
//            ignoreUnknownKeys = true
//            coerceInputValues = true
//        }
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient, forkTalesJson: Json): Retrofit {
//        return Retrofit.Builder()
//            .client(okHttpClient)
//            .addConverterFactory(MealJson.asConverterFactory("application/json".toMediaType()))
//            .baseUrl(Constants.BASE_URL + Constants.NUMBER + "/")
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideForkTalesApiService(retrofit: Retrofit): MealApi {
//        return retrofit.create(MealApi::class.java)
//    }
//}