package com.example.ra2somativa.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.ra2somativa.feature.data.model.QuestionData
import kotlinx.coroutines.delay

@Composable
fun QuizApp() {
    var questions by remember { mutableStateOf(QuestionData().loadQuestions()) }
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }
    var timer by remember { mutableIntStateOf(15) }
    var showIntermediateScreen by remember { mutableStateOf(false) }
    var intermediateTimer by remember { mutableIntStateOf(3) }
    var wasAnswerCorrect by remember { mutableStateOf(false) }
    var correctAnswer by remember { mutableStateOf("") }
    var playerScore by remember { mutableIntStateOf(1000) }
    var totalScore by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        questions = questions.shuffled().map { it.randomizeOptions() }
    }

    fun resetGame() {
        questions = QuestionData().loadQuestions().shuffled().map { it.randomizeOptions() }
        currentQuestionIndex = 0
        totalScore = 0
        timer = 15
        showResult = false
        showIntermediateScreen = false
        intermediateTimer = 3
        wasAnswerCorrect = false
        correctAnswer = ""
        playerScore = 1000
    }

    LaunchedEffect(currentQuestionIndex, showIntermediateScreen) {
        if (!showIntermediateScreen) {
            timer = 10
            playerScore = 1000
            while (timer > 0 && !showIntermediateScreen) {
                delay(1000L)
                timer--
                playerScore = timer * 1000 / 10 // Update player score based on remaining time
            }
            if (timer == 0) {
                playerScore = 0
                wasAnswerCorrect = false
                correctAnswer = questions[currentQuestionIndex].options[questions[currentQuestionIndex].correctAnswerIndex]
                showIntermediateScreen = true
            }
        }
    }

    if (showResult) {
        ResultScreen(totalScore) {
            resetGame()
        }
    } else if (showIntermediateScreen) {
        IntermediateScreen(intermediateTimer, wasAnswerCorrect, correctAnswer, playerScore) {
            totalScore += playerScore
            if (currentQuestionIndex == questions.size - 1) {
                showResult = true
            } else {
                currentQuestionIndex++
                timer = 15
                intermediateTimer = 3
                showIntermediateScreen = false
            }
        }
    } else {
        QuizScreen(
            question = questions[currentQuestionIndex],
            timer = timer,
            onAnswerSelected = { selectedIndex ->
                wasAnswerCorrect = selectedIndex == questions[currentQuestionIndex].correctAnswerIndex
                playerScore = if (wasAnswerCorrect) {
                    (timer * 1000) / 10 // Calcula a pontuação com base no tempo restante
                } else {
                    0 // Se a resposta estiver incorreta, o jogador não recebe pontos
                }
                correctAnswer = questions[currentQuestionIndex].options[questions[currentQuestionIndex].correctAnswerIndex]
                showIntermediateScreen = true
                intermediateTimer = 3
            }
        )
    }
}