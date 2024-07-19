package com.example.ra2somativa.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.ra2somativa.ui.theme.*

import com.example.ra2somativa.feature.data.model.Question
import com.example.ra2somativa.ui.button.ExitGameScreenButton
import kotlinx.coroutines.delay

@Composable
fun QuizScreen(question: Question, timer: Int, navController: NavHostController, onAnswerSelected: (Int) -> Unit) {
    var timeLeft by remember { mutableIntStateOf(timer) }
    val isAnswered by remember { mutableStateOf(false) }
    val context = LocalContext.current
    var isButtonEnabled by remember { mutableStateOf(true) }

    LaunchedEffect(timeLeft) {
        while (timeLeft > 0 && !isAnswered) {
            delay(1000L)
            timeLeft--
        }
        if (timeLeft == 0 && !isAnswered) {
            Log.d("QuizScreen", "Time is up. Navigating to next screen.")
            onAnswerSelected(-1) // If the timer runs out, treat as no answer
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = question.imageUrl),
                contentDescription = "Question Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            ExitGameScreenButton(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp),
                navController = navController
            )
            FloatingActionButton(
                onClick = {
                    if (isButtonEnabled) {
                        val randomTip = question.tips.random()
                        Toast.makeText(context, randomTip, Toast.LENGTH_SHORT).show()
                        isButtonEnabled = false
                    }
                          },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                backgroundColor = if (isButtonEnabled) option2 else Color.LightGray
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = question.questionText,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(24.dp))
        TimeRectangle(totalTime = timer)
        Spacer(modifier = Modifier.height(24.dp))
        AnswerGrid(
            answers = question.options,
            onAnswerSelected = onAnswerSelected
        )
    }
}

@Composable
fun TimeRectangle(totalTime: Int) {
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        expanded = true
    }

    val maxWidth = 300.dp  // Largura máxima da barra de tempo

    val width by animateDpAsState(
        targetValue = if (expanded) 0.dp else maxWidth,
        animationSpec = tween(durationMillis = totalTime * 1000),  // Duração da animação em milissegundos
        label = ""
    )

    val color = calculateColor(width)

    Box(
        modifier = Modifier
            .background(color)
            .width(width)
            .height(15.dp)
            .clip(RoundedCornerShape(16.dp))
    )
}

@Composable
fun calculateColor(currentWidth: Dp): Color {
    val green = Color(0, 255, 0)
    val yellow = Color(255, 255, 0)
    val red = Color(255, 0, 0)

    return when {
        currentWidth >= 200.dp -> {
            green
        }
        currentWidth >= 100.dp -> {
            lerp(yellow, green, (currentWidth - 100.dp) / 100.dp)
        }
        else -> {
            lerp(red, yellow, currentWidth / 100.dp)
        }
    }
}