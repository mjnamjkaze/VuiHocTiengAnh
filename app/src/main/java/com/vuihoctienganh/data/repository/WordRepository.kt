package com.vuihoctienganh.data.repository

import com.vuihoctienganh.data.db.dao.*
import com.vuihoctienganh.data.db.entity.*
import com.vuihoctienganh.data.source.DataSeeder
import com.vuihoctienganh.data.source.WordSource
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*

class WordRepository(
    private val wordDao: WordDao,
    private val userWordDao: UserWordDao,
    private val quizHistoryDao: QuizHistoryDao,
    private val dailyStatsDao: DailyStatsDao
) {
    // ===== Initialization =====

    suspend fun seedDatabaseIfNeeded() {
        val count = wordDao.getWordCount("coca")
        if (count == 0) {
            wordDao.insertAll(DataSeeder.getAllWords())
        }
    }

    // ===== Daily Words =====

    suspend fun getDailyNewWords(source: WordSource, levels: List<String>, count: Int = 5): List<WordEntity> {
        return wordDao.getNewWords(source.name.lowercase(), levels, count)
    }

    suspend fun getRandomDistractors(source: WordSource, levels: List<String>, excludeId: Int, count: Int = 3): List<WordEntity> {
        return wordDao.getRandomWords(source.name.lowercase(), levels, excludeId, count)
    }

    fun getWordsBySource(source: WordSource): Flow<List<WordEntity>> {
        return wordDao.getWordsBySource(source.name.lowercase())
    }

    suspend fun getWordById(id: Int): WordEntity? {
        return wordDao.getWordById(id)
    }

    // ===== User Progress =====

    suspend fun getUserWord(wordId: Int): UserWordEntity? {
        return userWordDao.getByWordId(wordId)
    }

    suspend fun markWordKnown(wordId: Int) {
        val existing = userWordDao.getByWordId(wordId)
        val now = System.currentTimeMillis()
        if (existing != null) {
            userWordDao.upsert(existing.copy(
                status = "learning",
                lastReviewed = now,
                nextReview = now + 1 * DAY_MILLIS,
                interval = 1,
                correctCount = existing.correctCount + 1,
                consecutiveCorrect = existing.consecutiveCorrect + 1
            ))
        } else {
            userWordDao.upsert(UserWordEntity(
                wordId = wordId,
                status = "learning",
                lastReviewed = now,
                nextReview = now + 1 * DAY_MILLIS,
                interval = 1,
                correctCount = 1,
                consecutiveCorrect = 1
            ))
        }
    }

    suspend fun markWordUnknown(wordId: Int) {
        val existing = userWordDao.getByWordId(wordId)
        val now = System.currentTimeMillis()
        if (existing != null) {
            userWordDao.upsert(existing.copy(
                status = "learning",
                lastReviewed = now,
                nextReview = now + 10 * 60 * 1000, // review in 10 mins
                interval = 0,
                wrongCount = existing.wrongCount + 1,
                consecutiveCorrect = 0
            ))
        } else {
            userWordDao.upsert(UserWordEntity(
                wordId = wordId,
                status = "learning",
                lastReviewed = now,
                nextReview = now + 10 * 60 * 1000,
                interval = 0,
                wrongCount = 1,
                consecutiveCorrect = 0
            ))
        }
    }

    suspend fun updateAfterReview(wordId: Int, correct: Boolean) {
        val existing = userWordDao.getByWordId(wordId) ?: return
        val now = System.currentTimeMillis()

        if (correct) {
            val newInterval = when {
                existing.interval == 0 -> 1
                existing.interval == 1 -> 3
                else -> (existing.interval * existing.easeFactor).toInt().coerceAtMost(30)
            }
            val newEF = (existing.easeFactor + 0.1f).coerceAtMost(3.0f)
            val newStatus = if (newInterval >= 30) "mastered" else "review"

            userWordDao.upsert(existing.copy(
                status = newStatus,
                interval = newInterval,
                easeFactor = newEF,
                nextReview = now + newInterval * DAY_MILLIS,
                lastReviewed = now,
                correctCount = existing.correctCount + 1,
                consecutiveCorrect = existing.consecutiveCorrect + 1
            ))
        } else {
            val newEF = (existing.easeFactor - 0.2f).coerceAtLeast(1.3f)
            userWordDao.upsert(existing.copy(
                status = "learning",
                interval = 1,
                easeFactor = newEF,
                nextReview = now + 1 * DAY_MILLIS,
                lastReviewed = now,
                wrongCount = existing.wrongCount + 1,
                consecutiveCorrect = 0
            ))
        }
    }

    // ===== Review =====

    fun getReviewWords(): Flow<List<UserWordEntity>> {
        return userWordDao.getReviewWords(System.currentTimeMillis())
    }

    fun getReviewCount(): Flow<Int> {
        return userWordDao.getReviewCount(System.currentTimeMillis())
    }

    fun getMasteredCount(): Flow<Int> {
        return userWordDao.getMasteredCount()
    }

    fun getLearnedCount(): Flow<Int> {
        return userWordDao.getLearnedCount()
    }

    fun getAllUserProgress(): Flow<List<UserWordEntity>> {
        return userWordDao.getAllProgress()
    }

    // ===== Quiz History =====

    suspend fun saveQuizResult(wordId: Int, quizType: String, answer: String, isCorrect: Boolean) {
        quizHistoryDao.insert(QuizHistoryEntity(
            wordId = wordId,
            quizType = quizType,
            answer = answer,
            isCorrect = isCorrect
        ))
    }

    fun getTotalCorrect(): Flow<Int> = quizHistoryDao.getTotalCorrect()
    fun getTotalAttempts(): Flow<Int> = quizHistoryDao.getTotalAttempts()

    // ===== Daily Stats =====

    suspend fun updateDailyStats(wordsLearned: Int = 0, wordsReviewed: Int = 0, quizCorrect: Int = 0, quizTotal: Int = 0) {
        val today = todayString()
        val existing = dailyStatsDao.getByDate(today)
        if (existing != null) {
            dailyStatsDao.upsert(existing.copy(
                wordsLearned = existing.wordsLearned + wordsLearned,
                wordsReviewed = existing.wordsReviewed + wordsReviewed,
                quizCorrect = existing.quizCorrect + quizCorrect,
                quizTotal = existing.quizTotal + quizTotal
            ))
        } else {
            dailyStatsDao.upsert(DailyStatsEntity(
                date = today,
                wordsLearned = wordsLearned,
                wordsReviewed = wordsReviewed,
                quizCorrect = quizCorrect,
                quizTotal = quizTotal
            ))
        }
    }

    suspend fun getStreak(): Int {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val calendar = Calendar.getInstance()
        var streak = 0

        while (true) {
            val dateStr = dateFormat.format(calendar.time)
            val stats = dailyStatsDao.getByDate(dateStr)
            if (stats != null && stats.wordsLearned > 0) {
                streak++
                calendar.add(Calendar.DAY_OF_YEAR, -1)
            } else {
                break
            }
        }
        return streak
    }

    fun getRecentStats(limit: Int = 30): Flow<List<DailyStatsEntity>> {
        return dailyStatsDao.getRecentStats(limit)
    }

    // ===== Helpers =====

    private fun todayString(): String {
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }

    companion object {
        private const val DAY_MILLIS = 24L * 60 * 60 * 1000
    }
}
