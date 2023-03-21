package com.example.guesstheword.screen.score

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
import com.example.guesstheword.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {

    // Variables for viewModel and viewModelFactory
    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_score,
            container,
            false
        )

        // Get args using by navArgs property delegate
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()

        // Initializing viewModelFactory and viewModel
        viewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)


        // Binding finalScore
        viewModel.score.observe(viewLifecycleOwner) { finalScore ->
            binding.scoreText.text = finalScore.toString()
        }

        // Setting _playAgain value to true
        binding.playAgainButton.setOnClickListener {
            viewModel.onPlayAgain()
        }

        // Setting playAgainButton
        viewModel.playAgain.observe(viewLifecycleOwner) { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                viewModel.onPlayAgainComplete()
            }
        }

        return binding.root
    }


}