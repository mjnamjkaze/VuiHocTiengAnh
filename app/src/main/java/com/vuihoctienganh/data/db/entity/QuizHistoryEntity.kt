package com.vuihoctienganh.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_history")
data class QuizHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val wordId: Int,
    val quizType: String,      // multiple_choice, fill_blank, match, listen
    val answer: String,
    val isCorrect: Boolean,
    val createdAt: Long = System.currentTimeMillis()
)
