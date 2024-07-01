package com.example.dishdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.dishdiary.presentation.AppNavGraph
import com.example.dishdiary.ui.theme.DishDiaryTheme

import com.example.dishdiary.viewmodel.UserViewModel
import com.example.dishdiary.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as MealApp).container.userRepository)
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DishDiaryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSizeClass = calculateWindowSizeClass(this)
                    val navController = rememberNavController()

                    // Tentukan start destination berdasarkan status autentikasi
                    val startDestination = if (isUserAuthenticated()) {
                        "meal"
                    } else {
                        "login"
                    }

                    AppNavGraph(
                        navController = navController,
                        userViewModel = userViewModel,
                        startDestination = startDestination,
                        windowSize = windowSizeClass.widthSizeClass
                    )
                }
            }
        }
    }

    private fun isUserAuthenticated(): Boolean {
        val sharedPreferences = getSharedPreferences("CineNestPrefs", MODE_PRIVATE)
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }
}
