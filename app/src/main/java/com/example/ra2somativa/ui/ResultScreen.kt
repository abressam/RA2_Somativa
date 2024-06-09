package com.example.ra2somativa.ui

import android.util.Log
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.example.ra2somativa.feature.presentation.PlayerViewModel
import kotlinx.coroutines.delay
import com.example.ra2somativa.ui.theme.*

@Composable
fun ResultScreen(
    playerViewModel: PlayerViewModel,
    currentUserNickname: String,
    onRestart: () -> Unit
) {
    val players by playerViewModel.players.observeAsState(emptyList())
    val maxScore = players.maxOfOrNull { it.score } ?: 1  // Garantir que maxScore não seja zero

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                text = "Ranking",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        // Adiciona as barras expansíveis
        itemsIndexed(players) { index, player ->
            ExpandingRectangle(index + 1, player.nickname, player.score, maxScore, currentUserNickname)
        }
        // Adiciona o botão "Recomeçar"
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onRestart,
                colors = ButtonDefaults.buttonColors(backgroundColor = button),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Recomeçar", fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun ExpandingRectangle(position: Int, nickname: String, score: Int, maxScore: Int, currentUserNickname: String) {
    var expanded by remember { mutableStateOf(false) }

    val displayedNickname = if (nickname.length > 4) {
        nickname.take(4) + "..." // Adiciona "..." ao final do apelido
    } else {
        nickname // Mantém o apelido original
    }

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

    Log.d("User_Object", "nickname: $nickname")
    Log.d("User_Object", "currentUserNickname: $currentUserNickname")

    val backgroundColor = if (nickname == currentUserNickname) {
        currentPlayer // Cor para o usuário atual
    } else {
        Color.Transparent // Cor padrão para outros usuários
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 15.dp)
            .height(35.dp)
            .fillMaxWidth()
            .background(color = backgroundColor)
    ) {
        Text(
            text = "${position}º",
            fontSize = 14.sp,
            modifier = Modifier.width(20.dp)
        )
        Text(
            text = displayedNickname,
            fontSize = 14.sp,
            modifier = Modifier.width(60.dp)
        )
        Box(
            modifier = Modifier
                .background(scoreBar)
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
