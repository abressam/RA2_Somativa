package com.example.ra2somativa.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.ra2somativa.feature.data.model.Question
import com.example.ra2somativa.ui.QuizScreen
import com.example.ra2somativa.ui.theme.RA2SomativaTheme


@Preview(showBackground = true)
@Composable
fun PreviewQuizScreen() {
    val sampleQuestion = Question(
        questionText = "Em qual país está localizado o Palácio de Potala?",
        options = listOf("Coréia do Norte", "Turquia", "Rússia", "China"),
        correctAnswerIndex = 3,
        imageUrl = "https://images.pexels.com/photos/8604524/pexels-photo-8604524.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
        tips = listOf("Tóquio", "Arroz", "Terremotos")
    )
    RA2SomativaTheme {
        QuizScreen(question = sampleQuestion, timer = 10) {}
    }
}