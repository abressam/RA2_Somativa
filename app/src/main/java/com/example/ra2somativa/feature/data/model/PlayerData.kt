package com.example.ra2somativa.feature.data.model

class PlayerData {
    fun loadPlayers(): List<Player> = listOf(
        Player(
            id = 0,
            nickname = "Mordecai",
            score = 8500,
            higherScore = 8500
        ),
        Player(
            id = 0,
            nickname = "Rigby",
            score = 7000,
            higherScore = 7000
        )
    )
}