package com.example.quizapp.repository

import android.content.Context
import com.example.quizapp.model.Question
import com.example.quizapp.model.QuestionDao
import com.example.quizapp.model.QuestionEntity
import com.example.quizapp.QuizApplication

class QuizRepository(private val apiService: QuizApiService,private val context: Context) {

    private val questionDao: QuestionDao by lazy{
        (context.applicationContext as QuizApplication).database.questionDao()
    }

    suspend fun fetchQuestionsFromApi(): List<Question>? {
        val response = apiService.getQuestions()
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun insertQuestions(questions: List<QuestionEntity>) {
        questionDao.insertQuestions(questions)
    }

    suspend fun getQuestionsFromDb(): List<QuestionEntity> {
        return questionDao.getAllQuestions()
    }
}