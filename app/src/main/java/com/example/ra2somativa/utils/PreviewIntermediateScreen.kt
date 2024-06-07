package com.example.ra2somativa.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ra2somativa.ui.IntermediateScreen
import com.example.ra2somativa.ui.theme.RA2SomativaTheme

@Preview(showBackground = true)
@Composable
fun PreviewIntermediateScreen() {
    RA2SomativaTheme {
        IntermediateScreen(
            timer = 3,
            wasAnswerCorrect = true,
            correctAnswer = "Paris",
            playerScore = 1000
        ) {}
    }
}