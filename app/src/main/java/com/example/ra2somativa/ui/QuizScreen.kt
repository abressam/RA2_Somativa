package com.example.ra2somativa.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.ra2somativa.feature.data.model.Question
import kotlinx.coroutines.delay

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