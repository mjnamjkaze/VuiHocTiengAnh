package com.vuihoctienganh.data.source

import com.vuihoctienganh.data.db.entity.WordEntity

/**
 * Seeds the database with all offline word sources on first launch.
 */
object DataSeeder {
    fun getAllWords(): List<WordEntity> {
        return CocaWords.getWords() +
                BncWords.getWords() +
                SlangWords.getWords() +
                IdiomWords.getWords()
    }

    fun getWordsBySource(source: WordSource): List<WordEntity> {
        return when (source) {
            WordSource.COCA -> CocaWords.getWords()
            WordSource.BNC -> BncWords.getWords()
            WordSource.SLANG -> SlangWords.getWords()
            WordSource.IDIOM -> IdiomWords.getWords()
        }
    }
}
