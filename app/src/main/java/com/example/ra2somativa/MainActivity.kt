package com.example.ra2somativa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.example.ra2somativa.navigation.Navigation
import com.example.ra2somativa.ui.QuizApp
import com.example.ra2somativa.ui.theme.RA2SomativaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RA2SomativaTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                }
            }
        }
    }
}
