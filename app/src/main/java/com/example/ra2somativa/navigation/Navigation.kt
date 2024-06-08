package com.example.ra2somativa.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ra2somativa.ui.QuizApp
import com.example.ra2somativa.ui.ResultScreen
import com.example.ra2somativa.ui.StartScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start_screen") {
        composable("start_screen") {
            ResultScreen() {
                navController.navigate("quiz_screen")
            }
        }
        composable("quiz_screen") {
            QuizApp()
        }
    }
}
