package com.vuihoctienganh.data.db.dao

import androidx.room.*
import com.vuihoctienganh.data.db.entity.WordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Query("SELECT * FROM words WHERE source = :source AND level IN (:levels) ORDER BY id")
    fun getWordsBySourceAndLevel(source: String, levels: List<String>): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE source = :source ORDER BY id")
    fun getWordsBySource(source: String): Flow<List<WordEntity>>

    @Query("SELECT * FROM words WHERE id = :id")
    suspend fun getWordById(id: Int): WordEntity?

    @Query("""
        SELECT w.* FROM words w
        LEFT JOIN user_words uw ON w.id = uw.wordId
        WHERE w.source = :source 
        AND w.level IN (:levels)
        AND (uw.status IS NULL OR uw.status = 'new')
        ORDER BY w.id
        LIMIT :limit
    """)
    suspend fun getNewWords(source: String, levels: List<String>, limit: Int): List<WordEntity>

    @Query("""
        SELECT w.* FROM words w
        WHERE w.source = :source AND w.level IN (:levels)
        AND w.id != :excludeId
        ORDER BY RANDOM()
        LIMIT :limit
    """)
    suspend fun getRandomWords(source: String, levels: List<String>, excludeId: Int, limit: Int): List<WordEntity>

    @Query("SELECT COUNT(*) FROM words WHERE source = :source")
    suspend fun getWordCount(source: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(words: List<WordEntity>)

    @Query("SELECT DISTINCT source FROM words")
    suspend fun getAvailableSources(): List<String>
}
