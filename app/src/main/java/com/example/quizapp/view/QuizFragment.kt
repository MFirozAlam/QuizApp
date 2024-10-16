package com.example.quizapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.repository.RetrofitClient
import com.example.quizapp.repository.QuizRepository
import com.example.quizapp.viewmodel.QuizViewModel
import com.example.quizapp.viewmodel.QuizViewModelFactory

class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private lateinit var viewModel: QuizViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the repository
        val repository = QuizRepository(
            RetrofitClient.instance,
            requireContext() // Pass context here
        )

        // Initialize the ViewModel using the factory and repository
        val viewModelFactory = QuizViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[QuizViewModel::class.java]

        // Fetch questions and observe data
        viewModel.fetchQuestions()

        viewModel.questionList.observe(viewLifecycleOwner) { questions ->
            // Display the questions on the screen
        }

        viewModel.timer.observe(viewLifecycleOwner) { timeLeft ->
            // Display the timer
        }

        viewModel.startQuizTimer(60000) // 60 seconds for example
    }
}