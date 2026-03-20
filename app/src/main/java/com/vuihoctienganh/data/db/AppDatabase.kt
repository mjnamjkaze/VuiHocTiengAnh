package com.vuihoctienganh.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vuihoctienganh.data.db.dao.*
import com.vuihoctienganh.data.db.entity.*

@Database(
    entities = [
        WordEntity::class,
        UserWordEntity::class,
        QuizHistoryEntity::class,
        DailyStatsEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun userWordDao(): UserWordDao
    abstract fun quizHistoryDao(): QuizHistoryDao
    abstract fun dailyStatsDao(): DailyStatsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "vuihoctienganh.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
