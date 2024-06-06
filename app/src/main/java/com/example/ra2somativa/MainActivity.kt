package com.example.ra2somativa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ra2somativa.ui.QuizApp
import com.example.ra2somativa.ui.theme.RA2SomativaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RA2SomativaTheme {
                QuizApp()
            }
        }
    }
}
