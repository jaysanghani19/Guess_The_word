package com.example.guesstheword.screen.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


// By using ViewModelFactory we will give Argument to ViewModel
class ScoreViewModelFactory(private val finalScore: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown Argument")
    }
}