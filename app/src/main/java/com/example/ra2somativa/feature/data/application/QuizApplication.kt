package com.example.ra2somativa.feature.data.application

import android.app.Application
import com.example.ra2somativa.feature.data.dao.populateDatabase
import com.example.ra2somativa.feature.data.database.QuizRoomDatabase
import com.example.ra2somativa.feature.data.model.PlayerData
import com.example.ra2somativa.feature.data.model.QuestionData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val database = QuizRoomDatabase.getDatabase(this)
        val playerDao = database.playerDao()
        val questionDao = database.questionDao()

        CoroutineScope(Dispatchers.IO).launch {
            if (playerDao.getAllPlayers().isEmpty()) {
                val players = PlayerData().loadPlayers()
                withContext(Dispatchers.IO) {
                    playerDao.populateDatabase(players)
                }
            }

            if (questionDao.getAllQuestions().isEmpty()) {
                val questions = QuestionData().loadQuestions()
                withContext(Dispatchers.IO) {
                    questionDao.populateDatabase(questions)
                }
            }
        }

    }
}
