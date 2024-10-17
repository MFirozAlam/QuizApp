package com.example.quizapp.view

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    //private lateinit var viewModel: QuizViewModel
    private val viewModel: QuizViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up ViewModel
        //viewModel = ViewModelProvider(this)[QuizViewModel::class.java]

        // Start the quiz
        view.findViewById<Button>(R.id.startQuizButton).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_quizFragment)
        }
    }
}
