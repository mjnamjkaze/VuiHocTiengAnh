package com.vuihoctienganh.data.db.dao

import androidx.room.*
import com.vuihoctienganh.data.db.entity.DailyStatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyStatsDao {
    @Query("SELECT * FROM daily_stats WHERE date = :date")
    suspend fun getByDate(date: String): DailyStatsEntity?

    @Query("SELECT * FROM daily_stats ORDER BY date DESC LIMIT :limit")
    fun getRecentStats(limit: Int): Flow<List<DailyStatsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(stats: DailyStatsEntity)

    @Query("SELECT COUNT(*) FROM daily_stats WHERE wordsLearned > 0")
    suspend fun getTotalActiveDays(): Int

    @Query("""
        SELECT COUNT(*) FROM (
            SELECT date FROM daily_stats 
            WHERE wordsLearned > 0 
            ORDER BY date DESC
        )
    """)
    suspend fun getCurrentStreak(): Int
}
