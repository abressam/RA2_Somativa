package com.example.ra2somativa.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ra2somativa.feature.data.model.PlayerData
import kotlinx.coroutines.delay

@Composable
fun ResultScreen(onRestart: () -> Unit) {
    val playerData = PlayerData()
    val players = remember { playerData.loadPlayers() } // Carrega a lista de jogadores

    // Variável para controlar o progresso da barra de progresso
    val progress by animateFloatAsState(
        targetValue = players.maxOfOrNull { it.score }?.toFloat() ?: 0f, // Maior score da lista
        animationSpec = tween(durationMillis = 2500)
    )

    // Restante do seu código para a tela de resultado, incluindo a exibição dos jogadores e do botão "Recomeçar"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ranking",
            fontSize = 24.sp,
            color = MaterialTheme.colors.onBackground
        )

        // Lista de jogadores com diferentes valores de pontuação
        players.forEach { player ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = player.nickname, // Nome do jogador
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.onSurface
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Barra de progresso ocupando apenas o espaço necessário
                    Box(
                        modifier = Modifier
                            .width(200.dp) // Largura fixa da barra de progresso
                            .height(16.dp) // Altura da barra de progresso
                    ) {
                        LinearProgressIndicator(
                            progress = progress / 15000f, // Progresso da barra de progresso
                            modifier = Modifier.fillMaxSize(),
                            color = Color.Green // Cor da barra de progresso
                        )
                    }

                    // Número do score ao final da barra
                    Text(
                        text = player.score.toString(),
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onSurface,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        // Botão "Recomeçar" no final da tela
        Button(
            onClick = onRestart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Recomeçar", fontSize = 18.sp)
        }
    }
}


