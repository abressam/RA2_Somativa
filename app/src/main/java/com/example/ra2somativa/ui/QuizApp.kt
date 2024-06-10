package com.example.ra2somativa.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import com.example.ra2somativa.feature.data.model.Player
import com.example.ra2somativa.feature.data.model.QuestionData
import com.example.ra2somativa.feature.presentation.PlayerViewModel
import kotlinx.coroutines.delay

@Composable
fun QuizApp(player: Player,
            playerViewModel: PlayerViewModel,
            navController: NavHostController) {
    var questions by remember { mutableStateOf(QuestionData().loadQuestions()) }
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var showResult by remember { mutableStateOf(false) }
    var timer by remember { mutableIntStateOf(10) }
    var showIntermediateScreen by remember { mutableStateOf(false) }
    var intermediateTimer by remember { mutableIntStateOf(3) }
    var wasAnswerCorrect by remember { mutableStateOf(false) }
    var correctAnswer by remember { mutableStateOf("") }
    var playerScore by remember { mutableIntStateOf(1000) }
    var totalScore by remember { mutableIntStateOf(0) }
    var higherScore by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        questions = questions.shuffled().map { it.randomizeOptions() }
    }

    fun resetGame() {
        questions = QuestionData().loadQuestions().shuffled().map { it.randomizeOptions() }
        currentQuestionIndex = 0
        totalScore = 0
        timer = 10
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
        ResultScreen(
            navController = navController,
            playerViewModel = playerViewModel,
            currentUserNickname = player.nickname,
        )
    } else if (showIntermediateScreen) {
        IntermediateScreen(intermediateTimer, wasAnswerCorrect, correctAnswer, playerScore) {
            totalScore += playerScore

            if (totalScore > higherScore) {
                higherScore = totalScore
                playerViewModel.updatePlayerScore(player.nickname, higherScore)
            }

            if (currentQuestionIndex == questions.size - 1) {
                showResult = true
            } else {
                currentQuestionIndex++
                timer = 10
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
                    (timer * 1000) / 10
                } else {
                    0
                }
                correctAnswer = questions[currentQuestionIndex].options[questions[currentQuestionIndex].correctAnswerIndex]
                showIntermediateScreen = true
                intermediateTimer = 3
            }
        )
    }
}