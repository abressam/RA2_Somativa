package com.example.ra2somativa.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ra2somativa.ui.theme.*

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
                            0 -> option1
                            1 -> option2
                            2 -> option3
                            3 -> option4
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