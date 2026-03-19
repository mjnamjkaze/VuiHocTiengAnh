package com.vuihoctienganh.data.source

/**
 * Enum representing available word sources.
 * Each source provides a different type of vocabulary.
 */
enum class WordSource(val displayName: String, val description: String, val emoji: String) {
    COCA("COCA", "Top spoken American English words", "🇺🇸"),
    BNC("BNC", "British National Corpus – academic & formal", "🇬🇧"),
    SLANG("Slang", "Modern slang & informal expressions", "🔥"),
    IDIOM("Idioms", "Common English idioms & phrases", "💬");

    companion object {
        fun fromString(value: String): WordSource {
            return entries.find { it.name.equals(value, ignoreCase = true) } ?: COCA
        }
    }
}
