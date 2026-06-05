package br.com.quiztourapp.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import br.com.quiztourapp.ui.InstructionScreen
import br.com.quiztourapp.ui.theme.RA2SomativaTheme

@Preview(showBackground = true)
@Composable
fun PreviewInstructionScreen() {
    val navController = rememberNavController()
    RA2SomativaTheme {
        InstructionScreen(navController = navController)
    }
}