package com.example.dishdiary.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dishdiary.data.network.model.RecipeDetails

@Database(
    entities = [RecipeDetails::class, UserEntity::class],
    version = 3,
    exportSchema = false
)
abstract class MealDatabase : RoomDatabase() {

    abstract fun mealDao(): MealDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: MealDatabase? = null

        fun getDatabase(context: Context): MealDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    "meal_database"
                )
                    .build()
                    .also { Instance = it }
            }
        }
    }
}

