package com.example.quizapp.repository

import com.example.quizapp.model.Question
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Retrofit

interface QuizApiService {
    @GET("api.php?amount=10&type=multiple")
    suspend fun getQuestions(): Response<List<Question>>
}
