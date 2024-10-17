package com.example.quizapp.view // or your package

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.quizapp.R // Update with your layout file
import com.example.quizapp.viewmodel.QuizViewModel
import com.example.quizapp.viewmodel.QuizViewModelFactory
//import com.example.quizapp.viewmodel.QuizViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class QuizFragment : Fragment() {

    @Inject
    lateinit var quizViewModelFactory: QuizViewModelFactory

    private val viewModel: QuizViewModel by viewModels { quizViewModelFactory }
    //private val viewModel: QuizViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe LiveData from ViewModel and update UI
        viewModel.questionList.observe(viewLifecycleOwner) { questions ->
            // Update UI with questions
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            // Show/hide loading indicator
        }

        viewModel.timer.observe(viewLifecycleOwner) { remainingTime ->
            // Update timer display
        }

        // Start the quiz timer (adjust totalTime as needed)
        viewModel.startQuizTimer(totalTime = 60000)
    }
}