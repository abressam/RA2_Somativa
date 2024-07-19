package com.example.ra2somativa.ui.button

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ExitGameScreenButton(modifier: Modifier, navController: NavHostController) {
    ExtendedFloatingActionButton(
        onClick = { navController.navigate("result_screen") },
        text = {
            Text(
                text = "Sair",
                color = Color.White
            )
               },
        icon = {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Exit game",
                tint = MaterialTheme.colors.onPrimary
            )
        },
        modifier = modifier
            .padding(4.dp) // Ajuste o padding conforme necessário
    )
}
