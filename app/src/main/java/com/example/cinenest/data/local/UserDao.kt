package com.example.cinenest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun registerUser(userEntity: UserEntity)

    @Query("SELECT * FROM users WHERE (username = :input OR email = :input) AND password = :password")
    suspend fun loginUser(input: String, password: String): UserEntity?
}
