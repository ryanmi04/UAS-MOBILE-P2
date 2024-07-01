package com.example.dishdiary

import android.app.Application
import com.example.dishdiary.di.AppContainer
import com.example.dishdiary.di.DefaultAppContainer
import com.example.dishdiary.domain.repository.UserRepository

class MealApp : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
