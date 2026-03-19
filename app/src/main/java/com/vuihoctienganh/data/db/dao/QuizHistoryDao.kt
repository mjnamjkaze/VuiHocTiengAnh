package com.vuihoctienganh.data.db.dao

import androidx.room.*
import com.vuihoctienganh.data.db.entity.QuizHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizHistoryDao {
    @Insert
    suspend fun insert(quizHistory: QuizHistoryEntity)

    @Query("SELECT * FROM quiz_history ORDER BY createdAt DESC LIMIT :limit")
    fun getRecent(limit: Int): Flow<List<QuizHistoryEntity>>

    @Query("SELECT COUNT(*) FROM quiz_history WHERE isCorrect = 1")
    fun getTotalCorrect(): Flow<Int>

    @Query("SELECT COUNT(*) FROM quiz_history")
    fun getTotalAttempts(): Flow<Int>

    @Query("""
        SELECT COUNT(*) FROM quiz_history 
        WHERE createdAt >= :startOfDay AND createdAt < :endOfDay AND isCorrect = 1
    """)
    suspend fun getDailyCorrect(startOfDay: Long, endOfDay: Long): Int

    @Query("""
        SELECT COUNT(*) FROM quiz_history 
        WHERE createdAt >= :startOfDay AND createdAt < :endOfDay
    """)
    suspend fun getDailyTotal(startOfDay: Long, endOfDay: Long): Int
}
