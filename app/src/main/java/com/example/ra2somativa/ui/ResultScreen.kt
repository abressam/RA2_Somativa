package com.example.ra2somativa.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ra2somativa.feature.di.RepositoryProvider
import com.example.ra2somativa.feature.presentation.PlayerViewModel
import com.example.ra2somativa.feature.presentation.PlayerViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun ResultScreen(
    playerViewModel: PlayerViewModel,
    onRestart: () -> Unit
) {
    val players by playerViewModel.players.observeAsState(emptyList())
    val maxScore = players.maxOfOrNull { it.score } ?: 1  // Garantir que maxScore não seja zero

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Adiciona as barras expansíveis
        itemsIndexed(players) { index, player ->
            ExpandingRectangle(index + 1, player.nickname, player.score, maxScore)
        }
        // Adiciona o botão "Recomeçar"
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRestart,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Recomeçar", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ExpandingRectangle(position: Int, nickname: String, score: Int, maxScore: Int) {
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(300)  // Optionally, delay the start of the animation
        expanded = true
    }

    val targetWidth = (score.toFloat() / maxScore) * 180.dp

    val width by animateDpAsState(
        targetValue = if (expanded) targetWidth else 0.dp,
        animationSpec = tween(durationMillis = 1500),
        label = ""
    )

    val animatedScore by animateIntAsState(
        targetValue = if (expanded) score else 0,
        animationSpec = tween(durationMillis = 1500),
        label = ""
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 15.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "${position}º",
            fontSize = 14.sp,
            modifier = Modifier.width(20.dp)
        )
        Text(
            text = nickname,
            fontSize = 14.sp,
            modifier = Modifier.width(60.dp)
        )
        Box(
            modifier = Modifier
                .background(Color.Blue)
                .width(width)
                .height(15.dp)
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = "$animatedScore pts",
            fontSize = 12.sp,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}
