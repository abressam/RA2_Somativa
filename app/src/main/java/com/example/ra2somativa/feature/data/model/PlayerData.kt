package com.example.ra2somativa.feature.data.model

class PlayerData {
    fun loadPlayers(): List<Player> = listOf(
        Player(
            id = 0,
            nickname = "Me supere!",
            score = 8500,
            higherScore = 8500
        )
    )
}