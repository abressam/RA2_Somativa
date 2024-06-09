package com.example.ra2somativa.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ra2somativa.feature.data.model.Player
import com.example.ra2somativa.feature.presentation.PlayerViewModel

@Composable
fun StartScreen(
    playerViewModel: PlayerViewModel,
    onStartClick: (Player) -> Unit
) {
    var nickname by remember { mutableStateOf("") }
    val addedPlayer by playerViewModel.addedPlayer.observeAsState()

    LaunchedEffect(addedPlayer) {
        addedPlayer?.let {
            onStartClick(it)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Digite seu apelido",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = nickname,
            onValueChange = { nickname = it },
            label = { Text(text = "Apelido") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                    playerViewModel.addPlayer(nickname)
                },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Começar a Jogar", fontSize = 18.sp)
        }
    }
}
