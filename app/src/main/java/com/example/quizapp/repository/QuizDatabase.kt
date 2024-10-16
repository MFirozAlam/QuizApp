package com.example.quizapp.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quizapp.model.QuestionEntity
import android.content.Context
import androidx.room.Room
import com.example.quizapp.model.QuestionDao

@Database(entities = [QuestionEntity::class], version = 1)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getInstance(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "quiz_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
