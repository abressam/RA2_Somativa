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

    fun addPlayer(nickname: String, onError: (String) -> Unit) {
        viewModelScope.launch {
            if (repository.getPlayerByNickname(nickname) == null) {
                val newPlayer = Player(nickname = nickname, score = 0, higherScore = 0)
                repository.insertPlayer(newPlayer)
                _addedPlayer.value = newPlayer
            } else {
                onError("Nickname já cadastrado. Por favor, escolha outro.")
            }
        }
    }

    fun updatePlayerScore(nickname: String, newScore: Int) {
        viewModelScope.launch {
            val player = repository.getPlayerByNickname(nickname)
            if (player != null) {
                // Atualizar o score do jogador
                val updatedPlayer = player.copy(score = newScore)
                repository.updatePlayer(updatedPlayer)
                fetchPlayers()
            } else {
                Log.e("PlayerViewModel", "Player not found with nickname: $nickname")
            }
        }
    }
}

class PlayerViewModelFactory(private val repository: PlayerRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}