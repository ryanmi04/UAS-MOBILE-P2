package com.example.dishdiary.domain.repository

import com.example.dishdiary.data.local.UserEntity

interface UserRepository {
    suspend fun registerUser(userEntity: UserEntity)
    suspend fun loginUser(input: String, password: String): UserEntity?
}