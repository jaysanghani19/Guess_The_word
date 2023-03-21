package com.example.guesstheword.screen.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore: Int) : ViewModel() {

    // Variable for finalScore
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // Variable for is player want to play game Again or not
    private val _playAgain = MutableLiveData<Boolean>()
    val playAgain: LiveData<Boolean>
        get() = _playAgain

    // Initializing scoreValue
    init {
        _score.value = finalScore
    }

    // function for playAgain

    fun onPlayAgain() {
        _playAgain.value = true
    }

    fun onPlayAgainComplete() {
        _playAgain.value = false
    }

}