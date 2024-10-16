package com.example.quizapp

import android.app.Application
import com.example.quizapp.repository.QuizDatabase

class QuizApplication : Application() {
    val database: QuizDatabase by lazy { QuizDatabase.getInstance(this) }
}