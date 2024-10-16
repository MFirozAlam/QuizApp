package com.example.quizapp.manager

import com.example.quizapp.model.Question

class QuizManager {

    private val questions = mutableListOf<Question>()

    fun addQuestion(question: Question) {
        questions.add(question)
    }

    fun getQuestion(index: Int): Question? {
        return if (index >= 0 && index < questions.size) {
            questions[index]
        } else {
            null
        }
    }

    fun getAllQuestions(): List<Question> {
        return questions
    }

    fun removeQuestion(question: Question) {
        questions.remove(question)
    }

    fun clearQuestions() {
        questions.clear()
    }
}