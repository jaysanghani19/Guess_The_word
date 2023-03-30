package com.example.guesstheword.screen.choice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
//import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentChoiceBinding

class ChoiceFragment : Fragment() {

    private lateinit var binding: FragmentChoiceBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Binding the layout to the ChoiceFragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_choice, container, false
        )

        // Setting ClickListener
        binding.nextButton.setOnClickListener {
            clickListener()
        }

        return binding.root
    }

    // Creating function for setOnClickOnListener
    private fun clickListener() {

        // Passing argument according the RadioButton chosen by user
        val choice: String = when (binding.choices.checkedRadioButtonId) {
            R.id.normal_words -> "Words"
            R.id.movie_word -> "Movies"
            R.id.place_word -> "Places"
            else -> "  "
        }


        findNavController().navigate(
            ChoiceFragmentDirections.actionChoiceFragmentToTimeFragment(choice)
        )

        Toast.makeText(requireContext(), " You chose $choice", Toast.LENGTH_SHORT).show()
    }
}