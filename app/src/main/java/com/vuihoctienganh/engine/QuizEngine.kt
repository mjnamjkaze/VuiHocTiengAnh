package com.vuihoctienganh.engine

import com.vuihoctienganh.data.db.entity.WordEntity

/**
 * Quiz question types and generation engine.
 */

data class QuizQuestion(
    val word: WordEntity,
    val type: QuizType,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val correctIndex: Int
)

enum class QuizType(val displayName: String) {
    MULTIPLE_CHOICE("Chọn nghĩa đúng"),
    REVERSE_CHOICE("Chọn từ đúng"),
    FILL_BLANK("Điền vào chỗ trống"),
    LISTEN("Nghe và chọn từ")
}

object QuizEngine {

    /**
     * Generate a quiz question from a word with distractors.
     */
    fun generateQuestion(
        word: WordEntity,
        distractors: List<WordEntity>,
        type: QuizType = QuizType.entries.random()
    ): QuizQuestion {
        return when (type) {
            QuizType.MULTIPLE_CHOICE -> multipleChoice(word, distractors)
            QuizType.REVERSE_CHOICE -> reverseChoice(word, distractors)
            QuizType.FILL_BLANK -> fillBlank(word, distractors)
            QuizType.LISTEN -> listenQuestion(word, distractors)
        }
    }

    /**
     * English word → choose Vietnamese meaning
     */
    private fun multipleChoice(word: WordEntity, distractors: List<WordEntity>): QuizQuestion {
        val options = (distractors.map { it.meaning } + word.meaning).shuffled()
        return QuizQuestion(
            word = word,
            type = QuizType.MULTIPLE_CHOICE,
            question = "\"${word.word}\" nghĩa là gì?",
            options = options,
            correctAnswer = word.meaning,
            correctIndex = options.indexOf(word.meaning)
        )
    }

    /**
     * Vietnamese meaning → choose English word
     */
    private fun reverseChoice(word: WordEntity, distractors: List<WordEntity>): QuizQuestion {
        val options = (distractors.map { it.word } + word.word).shuffled()
        return QuizQuestion(
            word = word,
            type = QuizType.REVERSE_CHOICE,
            question = "\"${word.meaning}\" trong tiếng Anh là gì?",
            options = options,
            correctAnswer = word.word,
            correctIndex = options.indexOf(word.word)
        )
    }

    /**
     * Fill in the blank using an example sentence
     */
    private fun fillBlank(word: WordEntity, distractors: List<WordEntity>): QuizQuestion {
        val examples = parseExamples(word.examples)
        val sentence = examples.firstOrNull() ?: "I use ${word.word} every day."
        val blanked = sentence.replace(word.word, "_____", ignoreCase = true)
        val options = (distractors.map { it.word } + word.word).shuffled()

        return QuizQuestion(
            word = word,
            type = QuizType.FILL_BLANK,
            question = "Điền vào chỗ trống:\n$blanked",
            options = options,
            correctAnswer = word.word,
            correctIndex = options.indexOf(word.word)
        )
    }

    /**
     * Listen to pronunciation → choose correct word
     */
    private fun listenQuestion(word: WordEntity, distractors: List<WordEntity>): QuizQuestion {
        val options = (distractors.map { it.word } + word.word).shuffled()
        return QuizQuestion(
            word = word,
            type = QuizType.LISTEN,
            question = "Nghe phát âm và chọn từ đúng 🔊",
            options = options,
            correctAnswer = word.word,
            correctIndex = options.indexOf(word.word)
        )
    }

    fun parseExamples(examplesJson: String): List<String> {
        return try {
            examplesJson
                .trimStart('[').trimEnd(']')
                .split("\",\"")
                .map { it.trim().trim('"') }
                .filter { it.isNotBlank() }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
