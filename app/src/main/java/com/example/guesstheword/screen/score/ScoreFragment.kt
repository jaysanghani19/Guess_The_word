package com.example.guesstheword.screen.score

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
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

        setHasOptionsMenu(true)

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

    // Creating Function for Sharing the Intent and Staring activity
    private fun shareIntent(){
        val args by navArgs<ScoreFragmentArgs>()
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(getString(R.string.intent, args.score))
            .setType("text/plain")
            .intent
        try {
//            Starting Activity to shareIntent
            startActivity(shareIntent)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(requireContext(), R.string.can_not_share, Toast.LENGTH_SHORT).show()
        }

    }

    // Creating option menu for share_menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu,menu)
    }

    // Selecting option menu options
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareIntent()
        }
        return super.onOptionsItemSelected(item)

    }

}