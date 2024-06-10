package com.example.ra2somativa.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.ra2somativa.ui.InstructionScreen
import com.example.ra2somativa.ui.theme.RA2SomativaTheme

@Preview(showBackground = true)
@Composable
fun PreviewInstructionScreen() {
    val navController = rememberNavController()
    RA2SomativaTheme {
        InstructionScreen(navController = navController)
    }
}