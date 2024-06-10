package com.example.ra2somativa.feature.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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

    @Update
    suspend fun updatePlayer(player: Player)

    @Query("SELECT * FROM players WHERE nickname = :nickname")
    suspend fun getPlayerByNickname(nickname: String): Player?
}

suspend fun PlayerDao.populateDatabase(players: List<Player>) {
    deleteAllPlayers()
    listPlayers(players)
}