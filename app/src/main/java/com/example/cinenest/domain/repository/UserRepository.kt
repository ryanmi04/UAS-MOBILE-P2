package com.example.cinenest.domain.repository

import com.example.cinenest.data.local.UserEntity

interface UserRepository {
    suspend fun registerUser(userEntity: UserEntity)
    suspend fun loginUser(input: String, password: String): UserEntity?
}