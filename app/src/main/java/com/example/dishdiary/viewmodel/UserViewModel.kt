package com.example.dishdiary.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dishdiary.data.local.UserEntity
import com.example.dishdiary.domain.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {
    private val _loginState = mutableStateOf<Result<UserEntity>?>(null)
    val loginState: Result<UserEntity>? get() = _loginState.value

    private val _registerState = mutableStateOf<Result<Boolean>?>(null)


    fun registerUser(userEntity: UserEntity) {
        viewModelScope.launch {
            try {
                repository.registerUser(userEntity)
                _registerState.value = Result.success(true) // Registration successful
            } catch (e: Exception) {
                _registerState.value = Result.failure(e) // Handle registration failure
            }
        }
    }

    fun loginUser(input: String, password: String) {
        viewModelScope.launch {
            _loginState.value = try {
                val user = repository.loginUser(input, password)
                if (user != null) {
                    Result.success(user)
                } else {
                    Result.failure(Exception("Invalid credentials"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}