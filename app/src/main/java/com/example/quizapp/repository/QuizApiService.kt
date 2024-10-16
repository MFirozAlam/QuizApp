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

object RetrofitClient {
    private const val BASE_URL = "https://opentdb.com/"

    val instance: QuizApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApiService::class.java)
    }
}
