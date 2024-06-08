package com.example.ra2somativa.feature.di

import android.content.Context
import androidx.room.Room
import com.example.ra2somativa.feature.data.database.QuizRoomDatabase
import com.example.ra2somativa.feature.data.repository.PlayerRepository

object RepositoryProvider {
    private var database: QuizRoomDatabase? = null

    fun providePlayerRepository(context: Context): PlayerRepository {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                QuizRoomDatabase::class.java,
                "app_database"
            ).build()
        }
        return PlayerRepository(database!!.playerDao())
    }
}