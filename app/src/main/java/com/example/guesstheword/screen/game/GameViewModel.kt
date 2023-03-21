package com.example.guesstheword.screen.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GameViewModel : ViewModel() {

    // Static Variable for total time , one second
    companion object {
        // 60 second of time for one game
        private val totalTime = 60000L

        private val oneSecond = 1000L

        private val finshTime = 0L
    }

    // CountDownTimer for counting time
    private val timer: CountDownTimer

    // Live time
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    // The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word


    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>


    // This varible will be true if user give answer to all 21 words else is will be false
    private val _isGameFinished = MutableLiveData<Boolean>()
    val isGameFinished: LiveData<Boolean>
        get() = _isGameFinished


    // Assigning Value and running functions
    init {
        _isGameFinished.value = false
        _score.value = 0
        resetList()
        nextWord()

        // Initializing The Timer from 60 second
        timer = object : CountDownTimer(totalTime, oneSecond) {

            override fun onTick(p0: Long) {
                _currentTime.value = p0.div(oneSecond)
            }

            override fun onFinish() {
                _currentTime.value = finshTime
                _isGameFinished.value = true
            }
        }
        timer.start()
    }


    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }


    /** Methods for buttons presses **/

    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        nextWord()
    }


    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
            resetList()
        }
        _word.value = wordList.removeAt(0)

    }

    // if ViewModel is cleared then cancel the timer
    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }
}