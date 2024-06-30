package com.example.cinenest

import android.app.Application
import com.example.cinenest.di.AppContainer
import com.example.cinenest.di.DefaultAppContainer
import com.example.cinenest.domain.repository.UserRepository

class MealApp : Application() {
    lateinit var container: AppContainer
    lateinit var userRepository: UserRepository

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
