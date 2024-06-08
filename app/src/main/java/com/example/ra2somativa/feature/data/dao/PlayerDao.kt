package com.example.ra2somativa.feature.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.ra2somativa.feature.data.model.Player

@Dao
interface PlayerDao {
    @Insert
    suspend fun insertPlayer(player: Player)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun listPlayers(players: List<Player>)

    @Query("SELECT * FROM players ORDER BY score DESC")
    fun getAllPlayers(): List<Player>

    @Query("DELETE FROM players")
    suspend fun deleteAllPlayers()
}

suspend fun PlayerDao.populateDatabase(players: List<Player>) {
    deleteAllPlayers()  // Limpa os dados antigos (opcional)
    listPlayers(players)
}