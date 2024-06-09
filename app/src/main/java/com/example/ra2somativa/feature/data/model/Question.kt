package com.example.ra2somativa.feature.data.model

data class Question(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val imageUrl: String,
    val tips: List<String>
) {
    fun randomizeOptions(): Question {
        val shuffledOptions = options.shuffled()
        val newCorrectAnswerIndex = shuffledOptions.indexOf(options[correctAnswerIndex])
        return copy(options = shuffledOptions, correctAnswerIndex = newCorrectAnswerIndex)
    }
}
