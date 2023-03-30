package com.example.guesstheword.screen.time

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guesstheword.R
import com.example.guesstheword.databinding.FragmentTimeBinding


class TimeFragment : Fragment() {

    private lateinit var binding: FragmentTimeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_time, container, false)

        binding.next.setOnClickListener {
            timeChoice()
        }

        return binding.root
    }

    private fun timeChoice() {
        val args by navArgs<TimeFragmentArgs>()

        val choice = args.choice

        val timeChoice: Int = when (binding.timeOption.checkedRadioButtonId) {
            R.id.one_minute_option -> 1
            R.id.two_minute_option -> 2
            R.id.five_minute_option -> 5
            else -> 1
        }

        findNavController().navigate(
            TimeFragmentDirections.actionTimeFragmentToGameDestination(
                choice,
                timeChoice
            )
        )

        Toast.makeText(requireContext(), "You Choose $timeChoice Time", Toast.LENGTH_SHORT).show()
    }
}