package com.example.quizapp.model

// Data class for individual questions
data class Question(
    val question: String,
    val correct_answer: String,
    val incorrect_answers: List<String>
)

// Response wrapper for the API response
data class QuizResponse(
    val response_code: Int,
    val results: List<Question>
)
