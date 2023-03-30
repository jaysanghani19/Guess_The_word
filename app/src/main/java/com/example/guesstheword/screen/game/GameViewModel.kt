package com.example.guesstheword.screen.game

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class GameViewModel(choice: String, timeChoice: Int) : ViewModel() {


    private var totalTime = 60000L

    private var oneSecond = 1000L

    private var finshTime = 0L


    // Setting choice entered by user
    private val _userChoice = MutableLiveData<String>()
    val userChoice: LiveData<String>
        get() = _userChoice


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

    // The list of places,movies,words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>


    // This varible will be true if user give answer to all 21 words else is will be false
    private val _isGameFinished = MutableLiveData<Boolean>()
    val isGameFinished: LiveData<Boolean>
        get() = _isGameFinished

    private val _userTime = MutableLiveData<Int>()
    val usertime: LiveData<Int>
        get() = _userTime


    // Assigning Value and running functions
    init {
        _userTime.value = timeChoice
        _userChoice.value = choice
        _isGameFinished.value = false
        _score.value = 0
        resetList()
        nextWord()
        setTime()


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

    private fun setTime() {
        when (_userTime.value) {
            1 -> totalTime = totalTime * 1
            2 -> totalTime = totalTime * 2
            5 -> totalTime = totalTime * 5
        }
    }


    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        // Assigning wordList as user give input about the list
        when (_userChoice.value) {
            "Movies" -> {
                wordList = mutableListOf(
                    "John Wick : Chapter 1",
                    "Terminator",
                    "Iron man I",
                    "Captain America : Winter Soldier",
                    "Catch Me If You Can",
                    "Inception",
                    "Tenet",
                    "Openhimer",
                    "Intersteller",
                    "Harry Potter",
                    "Mission Impossible",
                    "Pirates of Caribbean",
                    "Jack Reacher",
                    "The Batman",
                    "Avengers : Endgame",
                    "Avengers : Infinity War"
                )
            }
            "Places" -> {
                wordList = mutableListOf(
                    "Mumbai",
                    "Chennai",
                    "Surat",
                    "Kolkata",
                    "Bengaluru",
                    "Paris",
                    "Moscow",
                    "New York",
                    "London",
                    "New Delhi",
                    "Munich",
                    "Berlin",
                    "Sydney",
                    "Perth",
                    "Hong Kong",
                    "Tokyo"
                )
            }
            "Words" -> {
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

            }
        }
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