package com.example.ra2somativa.feature.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ra2somativa.feature.data.model.Player
import com.example.ra2somativa.feature.data.repository.PlayerRepository
import kotlinx.coroutines.launch

class PlayerViewModel(private val repository: PlayerRepository) : ViewModel() {
    private val _players = MutableLiveData<List<Player>>()
    val players: LiveData<List<Player>> get() = _players

    private val _addedPlayer = MutableLiveData<Player>()
    val addedPlayer: LiveData<Player> get() = _addedPlayer

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            _players.value = repository.getAllPlayers()
        }
    }

     fun addPlayer(nickname: String) {
        viewModelScope.launch {
            val player = Player(nickname = nickname, score = 0, higherScore = 0)
            repository.insertPlayer(player)
            fetchPlayers()
            _addedPlayer.value = player
        }
    }

    fun updatePlayerScore(nickname: String, newScore: Int) {
        viewModelScope.launch {
            // Buscar o jogador pelo nickname
            val player = repository.getPlayerByNickname(nickname)
            // Verificar se o jogador foi encontrado
            if (player != null) {
                // Atualizar o score do jogador
                val updatedPlayer = player.copy(score = newScore)
                repository.updatePlayer(updatedPlayer)
                // Atualizar a lista de jogadores (opcional)
                fetchPlayers()
            } else {
                Log.e("PlayerViewModel", "Player not found with nickname: $nickname")
            }
        }
    }
}

// Factory para criar o ViewModel
class PlayerViewModelFactory(private val repository: PlayerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}