package com.vuihoctienganh

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.vuihoctienganh.data.db.AppDatabase
import com.vuihoctienganh.data.repository.WordRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "settings")

class VuiHocApp : Application() {
    val database by lazy { AppDatabase.getInstance(this) }
    val repository by lazy {
        WordRepository(
            database.wordDao(),
            database.userWordDao(),
            database.quizHistoryDao(),
            database.dailyStatsDao()
        )
    }

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        // Seed database on first launch
        applicationScope.launch {
            repository.seedDatabaseIfNeeded(this@VuiHocApp)
        }
    }
}
