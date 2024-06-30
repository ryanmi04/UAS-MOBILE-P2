package com.example.cinenest.data.repository

import com.example.cinenest.data.local.UserDao
import com.example.cinenest.data.local.UserEntity
import com.example.cinenest.domain.repository.UserRepository

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun registerUser(userEntity: UserEntity) {
        userDao.registerUser(userEntity)
    }

    override suspend fun loginUser(input: String, password: String): UserEntity? {
        return userDao.loginUser(input, password)
    }
}

