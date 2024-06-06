package com.example.ra2somativa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.ra2somativa.feature.data.model.Question
import com.example.ra2somativa.feature.data.model.QuestionData
import com.example.ra2somativa.ui.theme.RA2SomativaTheme
import kotlinx.coroutines.delay


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

@Composable
fun QuizScreen(question: Question, timer: Int, onAnswerSelected: (Int) -> Unit) {
    var timeLeft by remember { mutableIntStateOf(timer) }
    val isAnswered by remember { mutableStateOf(false) }

    LaunchedEffect(timeLeft) {
        while (timeLeft > 0 && !isAnswered) {
            delay(1000L)
            timeLeft--
        }
        if (timeLeft == 0 && !isAnswered) {
            onAnswerSelected(-1) // If the timer runs out, treat as no answer
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = question.imageUrl),
            contentDescription = "Question Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = question.questionText,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Tempo restante: $timer segundos",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(24.dp))
        AnswerGrid(
            answers = question.options,
            onAnswerSelected = onAnswerSelected
        )
    }
}

@Composable
fun IntermediateScreen(
    timer: Int,
    wasAnswerCorrect: Boolean,
    correctAnswer: String,
    playerScore: Int,
    onTimerFinish: () -> Unit) {

    var intermediateTimer by remember { mutableIntStateOf(timer) }

    LaunchedEffect(intermediateTimer) {
        while (intermediateTimer > 0) {
            delay(1000L)
            intermediateTimer--
        }
        onTimerFinish()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (wasAnswerCorrect) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Correct Answer",
                        tint = Color.Green,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Você acertou!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Green
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Sua pontuação: $playerScore",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        } else {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = "Incorrect Answer",
                        tint = Color.Red,
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Você errou!",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "A resposta correta é: $correctAnswer",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Sua pontuação: $playerScore",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Próxima pergunta em: $intermediateTimer segundos",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
fun AnswerGrid(answers: List<String>, onAnswerSelected: (Int) -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        answers.chunked(2).forEachIndexed { rowIndex, rowAnswers ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowAnswers.forEachIndexed { columnIndex, answer ->
                    val index = rowIndex * 2 + columnIndex
                    AnswerButton(
                        answer = answer,
                        modifier = Modifier.weight(1f),
                        backgroundColor = when (index) {
                            0 -> Color.Red
                            1 -> Color.Cyan
                            2 -> Color.Green
                            3 -> Color.Yellow
                            else -> Color.LightGray
                        }
                    ) {
                        onAnswerSelected(index)
                    }
                }
            }
        }
    }
}

@Composable
fun AnswerButton(answer: String, modifier: Modifier = Modifier, backgroundColor: Color, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor, contentColor = Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxHeight()
    ) {
        Text(
            text = answer,
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ResultScreen(totalScore: Int, onRestart: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pontuação total: $totalScore",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = onRestart,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Recomeçar", fontSize = 18.sp)
        }
    }
}
