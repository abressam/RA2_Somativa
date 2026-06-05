package br.com.quiztourapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.quiztourapp.ui.IntermediateScreen
import br.com.quiztourapp.ui.theme.RA2SomativaTheme

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