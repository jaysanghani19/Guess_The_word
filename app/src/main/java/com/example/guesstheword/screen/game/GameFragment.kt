package com.example.guesstheword.screen.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    // ViewModelFactory for GameFragment
    private lateinit var viewModelFactory: GameViewModelFactory

    // ViewModel for GameFragment
    private lateinit var viewModel: GameViewModel

    // DataBinding for fragment_game
    private lateinit var binding: FragmentGameBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )

        val gameArguments by navArgs<GameFragmentArgs>()

        // Initializing viewModel and viewModelFactory

        viewModelFactory = GameViewModelFactory(gameArguments.choice)

        viewModel = ViewModelProvider(this, viewModelFactory).get(GameViewModel::class.java)

        // Setting setOnClickListener for both buttons

        binding.correctButton.setOnClickListener {
            viewModel.onCorrect()
        }

        binding.skipButton.setOnClickListener {
            viewModel.onSkip()
        }

        // Updating Score using livedata from ViewModel

        viewModel.score.observe(viewLifecycleOwner)
        { newScore ->
            binding.scoreText.text = newScore.toString()
        }

        // Updating Word using livedata from ViewModel

        viewModel.word.observe(viewLifecycleOwner)
        { newWord ->
            binding.wordText.text = newWord.toString()
        }

        // Displaying currentTime to Layout

        viewModel.currentTime.observe(viewLifecycleOwner)
        { currentTime ->
            binding.timerText.text = currentTime.toString()
        }

        // When time is over then it will navigate to ScoreFragment

        viewModel.isGameFinished.observe(viewLifecycleOwner)
        { isGameFinished ->
            if (isGameFinished) {
                val tempScore = viewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameToScore(tempScore)
                findNavController().navigate(action)
            }
        }

        return binding.root
    }


}