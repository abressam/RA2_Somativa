package com.example.ra2somativa.feature.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val imageUrl: String
) {
    fun randomizeOptions(): Question {
        val shuffledOptions = options.shuffled()
        val newCorrectAnswerIndex = shuffledOptions.indexOf(options[correctAnswerIndex])
        return copy(options = shuffledOptions, correctAnswerIndex = newCorrectAnswerIndex)
    }
}
