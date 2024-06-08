package com.example.ra2somativa.feature.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ra2somativa.feature.data.model.Player
import com.example.ra2somativa.feature.data.model.Question

@Dao
interface QuestionDao {
    @Insert
    suspend fun insertQuestion(question: Question)

    @Query("SELECT * FROM questions")
    fun getAllQuestions(): List<Question>
}

suspend fun QuestionDao.populateDatabase(questions: List<Question>) {
    questions.forEach { question ->
        insertQuestion(question)
    }
}
