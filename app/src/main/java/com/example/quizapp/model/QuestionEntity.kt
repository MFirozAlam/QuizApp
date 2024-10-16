package com.example.quizapp.model


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: String
)