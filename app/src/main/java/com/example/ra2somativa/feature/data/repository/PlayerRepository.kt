package com.example.ra2somativa.feature.data.repository

import com.example.ra2somativa.feature.data.dao.PlayerDao
import com.example.ra2somativa.feature.data.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayerRepository(private val playerDao: PlayerDao) {
    suspend fun getAllPlayers(): List<Player> {
        return withContext(Dispatchers.IO) {
            playerDao.getAllPlayers()
        }
    }

}