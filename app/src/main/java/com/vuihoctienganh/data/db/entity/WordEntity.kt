package com.vuihoctienganh.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "words")
data class WordEntity(
    @PrimaryKey val id: Int,
    val word: String,
    val ipa: String,
    val meaning: String,
    val partOfSpeech: String,
    val level: String,        // A1, A2, B1, B2
    val topic: String,        // daily, food, travel, work, etc.
    val source: String,       // coca, bnc, slang, idiom
    val examples: String      // JSON array of example sentences
)
