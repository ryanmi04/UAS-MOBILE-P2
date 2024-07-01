package com.example.dishdiary.presentation

import androidx.compose.runtime.Composable
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dishdiary.presentation.screen.LoginScreen
import com.example.dishdiary.presentation.screen.RegisterScreen
import com.example.dishdiary.presentation.core.MealScreen
import com.example.dishdiary.viewmodel.UserViewModel

@Composable
fun AppNavGraph(
    navController: NavHostController,
    userViewModel: UserViewModel,
    startDestination: String,
    windowSize: WindowWidthSizeClass
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                userViewModel = userViewModel,
                onLoginSuccess = {
                    navController.navigate("meal") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }
        composable("register") {
            RegisterScreen(
                userViewModel = userViewModel,
                onRegisterSuccess = {
                    navController.navigate("login") {
                        popUpTo("register") { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.navigate("login")
                }
            )
        }
        composable("meal") {
            MealScreen(windowSize = windowSize)
        }
    }
}
