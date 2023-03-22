package com.example.guesstheword.screen.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

// By using ViewModelFactory we will give Argument to ViewModel

@Suppress("UNCHECKED_CAST")
class GameViewModelFactory(private val choice : String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java)){
            return GameViewModel(choice) as T
        }
        throw IllegalArgumentException("Unknown Argument Type")
    }
}