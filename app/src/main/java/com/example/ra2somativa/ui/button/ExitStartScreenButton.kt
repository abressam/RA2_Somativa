package com.example.ra2somativa.ui.button

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ra2somativa.ui.theme.option2
import kotlin.system.exitProcess

fun exitApp(context: Context) {
    if (context is Activity) {
        context.finishAffinity()
        exitProcess(0)
    }
}
@Composable
fun ExitButton(modifier: Modifier = Modifier, context: Context) {
    Button(
        onClick = { exitApp(context) },
        colors = ButtonDefaults.buttonColors(backgroundColor = option2),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Sair",
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = TextStyle(
                lineHeight = 24.sp
            )
        )
    }
}
