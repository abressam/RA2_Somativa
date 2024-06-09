package com.example.ra2somativa.feature.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.ra2somativa.feature.data.dao.PlayerDao
import com.example.ra2somativa.feature.data.model.Converters
import com.example.ra2somativa.feature.data.model.Player

@Database(entities = [Player::class], version = 2)
@TypeConverters(Converters::class)
abstract class QuizRoomDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao

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
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}