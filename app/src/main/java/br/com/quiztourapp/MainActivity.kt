package br.com.quiztourapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import br.com.quiztourapp.navigation.Navigation
import br.com.quiztourapp.ui.theme.RA2SomativaTheme


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
