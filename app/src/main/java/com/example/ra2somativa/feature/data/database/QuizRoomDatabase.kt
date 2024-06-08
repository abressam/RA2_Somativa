package com.example.ra2somativa.feature.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ra2somativa.feature.data.dao.PlayerDao
import com.example.ra2somativa.feature.data.dao.QuestionDao
import com.example.ra2somativa.feature.data.dao.populateDatabase
import com.example.ra2somativa.feature.data.model.Converters
import com.example.ra2somativa.feature.data.model.Player
import com.example.ra2somativa.feature.data.model.PlayerData
import com.example.ra2somativa.feature.data.model.Question
import com.example.ra2somativa.feature.data.model.QuestionData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Player::class, Question::class], version = 1)
@TypeConverters(Converters::class)
abstract class QuizRoomDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: QuizRoomDatabase? = null

        fun getDatabase(context: Context): QuizRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizRoomDatabase::class.java,
                    "app_database"
                )
                    .addCallback(PlayerDatabaseCallback(context))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class PlayerDatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    val players = PlayerData().loadPlayers()
                    val questions = QuestionData().loadQuestions()

                    getDatabase(context).playerDao().populateDatabase(players)
                    getDatabase(context).questionDao().populateDatabase(questions)

                }
            }
        }
    }
}