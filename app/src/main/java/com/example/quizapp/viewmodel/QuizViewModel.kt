package com.example.quizapp.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.quizapp.model.Question
import com.example.quizapp.model.QuestionEntity
import com.example.quizapp.repository.QuizRepository

class QuizViewModel(private val repository: QuizRepository) : ViewModel() {

    private val _questionList = MutableLiveData<List<Question>?>()
    val questionList: MutableLiveData<List<Question>?> = _questionList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _timer = MutableLiveData<Long>()
    val timer: LiveData<Long> = _timer

    init {
        fetchQuestions()
    }

    fun fetchQuestions() {
        viewModelScope.launch {
            _isLoading.value = true
            val questions = repository.fetchQuestionsFromApi()
            if (questions != null) {
                _questionList.value = questions
                repository.insertQuestions(questions.map {
                    QuestionEntity(
                        0, it.question, it.correct_answer, it.incorrect_answers.joinToString(",")
                    )
                })
            } else {
                // Fetch from local DB if API call fails
                val localQuestions = repository.getQuestionsFromDb()
                _questionList.value = localQuestions.map {
                    Question(it.question, it.correctAnswer, it.incorrectAnswers.split(","))
                }
            }
            _isLoading.value = false
        }
    }

    fun startQuizTimer(totalTime: Long) {
        object : CountDownTimer(totalTime, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timer.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                _timer.value = 0
                // Handle quiz end
            }
        }.start()
    }
}
class QuizViewModelFactory(private val repository: QuizRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuizViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return QuizViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}