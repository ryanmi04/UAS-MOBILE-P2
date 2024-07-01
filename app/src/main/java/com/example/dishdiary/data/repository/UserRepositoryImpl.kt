package com.example.dishdiary.data.repository

import com.example.dishdiary.data.local.UserDao
import com.example.dishdiary.data.local.UserEntity
import com.example.dishdiary.domain.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun registerUser(userEntity: UserEntity) {
        userDao.registerUser(userEntity)
    }

    override suspend fun loginUser(input: String, password: String): UserEntity? {
        return userDao.loginUser(input, password)
    }
}

