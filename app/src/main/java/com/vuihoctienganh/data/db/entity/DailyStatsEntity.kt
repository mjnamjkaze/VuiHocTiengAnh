package com.vuihoctienganh.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_stats")
data class DailyStatsEntity(
    @PrimaryKey val date: String,       // "2026-03-20"
    val wordsLearned: Int = 0,
    val wordsReviewed: Int = 0,
    val quizCorrect: Int = 0,
    val quizTotal: Int = 0,
    val timeSpentSeconds: Int = 0
)
