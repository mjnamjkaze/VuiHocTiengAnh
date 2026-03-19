package com.vuihoctienganh.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_words")
data class UserWordEntity(
    @PrimaryKey val wordId: Int,
    val status: String = "new",           // new, learning, review, mastered
    val interval: Int = 0,                // days until next review
    val easeFactor: Float = 2.5f,         // SM-2 ease factor
    val nextReview: Long = 0L,            // epoch millis
    val lastReviewed: Long = 0L,          // epoch millis
    val correctCount: Int = 0,
    val wrongCount: Int = 0,
    val consecutiveCorrect: Int = 0
)
