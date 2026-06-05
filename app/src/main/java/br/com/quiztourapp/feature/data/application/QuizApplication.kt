package br.com.quiztourapp.feature.data.application

import android.app.Application
import br.com.quiztourapp.feature.data.dao.populateDatabase
import br.com.quiztourapp.feature.data.database.QuizRoomDatabase
import br.com.quiztourapp.feature.data.model.PlayerData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuizApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val database = QuizRoomDatabase.getDatabase(this)
        val playerDao = database.playerDao()

        CoroutineScope(Dispatchers.IO).launch {
            if (playerDao.getAllPlayers().isEmpty()) {
                val players = PlayerData().loadPlayers()
                withContext(Dispatchers.IO) {
                    playerDao.populateDatabase(players)
                }
            }
        }
    }
}
