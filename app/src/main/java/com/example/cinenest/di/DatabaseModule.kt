//package com.example.cinenest.di
//
//import android.content.Context
//import androidx.room.Room
//import com.example.cinenest.data.local.MealDatabase
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.qualifiers.ApplicationContext
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//
//object DatabaseModule {
//        @Provides
//        @Singleton
//        fun provideMealDatabase(@ApplicationContext context: Context): MealDatabase {
//            return Room.databaseBuilder(
//                context.applicationContext,
//                MealDatabase::class.java,
//                "meal_database"
//            ).fallbackToDestructiveMigration()
//                .build()
//        }
//}