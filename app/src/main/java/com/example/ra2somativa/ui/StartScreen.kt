package com.example.ra2somativa.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.ra2somativa.ui.theme.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ra2somativa.feature.data.model.Player
import com.example.ra2somativa.feature.presentation.PlayerViewModel

@Composable
fun StartScreen(
    navController: NavHostController,
    playerViewModel: PlayerViewModel,
    onStartClick: (Player) -> Unit
) {
    var nickname by remember { mutableStateOf("") }
    val addedPlayer by playerViewModel.addedPlayer.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(addedPlayer) {
        addedPlayer?.let {
            onStartClick(it)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = backgroundScreen, shape = RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "QuizTour",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )
            Spacer(modifier = Modifier.height(16.dp))
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
                placeholder = {
                        Text(
                            text = "Apelido",
                            color = focusTextField
                        )
                },
                singleLine = true, // Garantir que o campo tenha apenas uma linha
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (nickname.isNotEmpty()) {
                            playerViewModel.addPlayer(
                                nickname,
                                onError = { errorMessage ->
                                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                                }
                            )
                        }
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Start),
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = textFieldBackground,
                    focusedIndicatorColor = focusTextField,
                    unfocusedIndicatorColor = textFieldBackground
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (nickname.isNotEmpty()) {
                        playerViewModel.addPlayer(
                            nickname,
                            onError = { errorMessage ->
                                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = button),
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = nickname.isNotEmpty()
            ) {
                Text(text = "Começar a Jogar", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate("instruction_screen")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = option1),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Instruções", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navController.navigate("result_screen")
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = option4),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Ranking", fontSize = 18.sp)
            }
        }
    }
}


