package com.vuihoctienganh.data.source

import android.content.Context
import com.vuihoctienganh.data.db.entity.WordEntity

/**
 * Seeds the database with all offline word sources on first launch.
 */
object DataSeeder {
    fun getAllWords(context: Context): List<WordEntity> {
        return CocaWords.getWords() +
                BncWords.getWords() +
                SlangWords.getWords() +
                IdiomWords.getWords() +
                loadCommonWords(context)
    }

    fun getWordsBySource(source: WordSource): List<WordEntity> {
        return when (source) {
            WordSource.COCA -> CocaWords.getWords()
            WordSource.BNC -> BncWords.getWords()
            WordSource.SLANG -> SlangWords.getWords()
            WordSource.IDIOM -> IdiomWords.getWords()
        }
    }

    private fun loadCommonWords(context: Context): List<WordEntity> {
        val files = listOf(
            "words_a1_1.json", "words_a1_2.json",
            "words_a2_1.json", "words_a2_2.json",
            "words_b1_1.json", "words_b1_2.json",
            "words_b2_1.json", "words_b2_2.json"
        )
        val all = mutableListOf<WordEntity>()
        for (file in files) {
            try {
                all.addAll(JsonWordLoader.loadFromAsset(context, file, "coca"))
            } catch (_: Exception) {}
        }
        return all
    }
}
