package com.example.cinenest

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
import com.example.cinenest.presentation.AppNavGraph
import com.example.cinenest.ui.theme.CineNestTheme
import com.example.cinenest.viewmodel.UserViewModel
import com.example.cinenest.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    private val userViewModel: UserViewModel by viewModels {
        UserViewModelFactory((application as MealApp).container.userRepository)
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CineNestTheme {
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
