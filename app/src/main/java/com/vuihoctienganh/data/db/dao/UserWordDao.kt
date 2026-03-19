package com.vuihoctienganh.data.db.dao

import androidx.room.*
import com.vuihoctienganh.data.db.entity.UserWordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserWordDao {
    @Query("SELECT * FROM user_words WHERE wordId = :wordId")
    suspend fun getByWordId(wordId: Int): UserWordEntity?

    @Query("SELECT * FROM user_words WHERE nextReview <= :now AND status != 'mastered' ORDER BY nextReview ASC")
    fun getReviewWords(now: Long): Flow<List<UserWordEntity>>

    @Query("SELECT COUNT(*) FROM user_words WHERE nextReview <= :now AND status != 'mastered'")
    fun getReviewCount(now: Long): Flow<Int>

    @Query("SELECT COUNT(*) FROM user_words WHERE status = 'mastered'")
    fun getMasteredCount(): Flow<Int>

    @Query("SELECT COUNT(*) FROM user_words WHERE status != 'new'")
    fun getLearnedCount(): Flow<Int>

    @Query("SELECT * FROM user_words ORDER BY lastReviewed DESC")
    fun getAllProgress(): Flow<List<UserWordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(userWord: UserWordEntity)

    @Query("SELECT COUNT(*) FROM user_words WHERE status = :status")
    suspend fun countByStatus(status: String): Int
}
