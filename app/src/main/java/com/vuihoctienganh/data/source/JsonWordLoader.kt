package com.vuihoctienganh.data.source

import android.content.Context
import com.vuihoctienganh.data.db.entity.WordEntity
import org.json.JSONArray

/**
 * Loads word data from JSON asset files.
 * Format: [{"id":5001,"w":"hello","p":"/həˈloʊ/","m":"xin chào","s":"noun","l":"A1","t":"daily","e":["ex1","ex2","ex3"]}]
 */
object JsonWordLoader {
    fun loadFromAsset(context: Context, filename: String, source: String): List<WordEntity> {
        val words = mutableListOf<WordEntity>()
        try {
            val json = context.assets.open(filename).bufferedReader().use { it.readText() }
            val arr = JSONArray(json)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val examples = obj.getJSONArray("e")
                val exList = mutableListOf<String>()
                for (j in 0 until examples.length()) {
                    exList.add(examples.getString(j))
                }
                words.add(WordEntity(
                    id = obj.getInt("id"),
                    word = obj.getString("w"),
                    ipa = obj.getString("p"),
                    meaning = obj.getString("m"),
                    partOfSpeech = obj.getString("s"),
                    level = obj.getString("l"),
                    topic = obj.getString("t"),
                    source = source,
                    examples = """[${exList.joinToString(",") { "\"$it\"" }}]"""
                ))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return words
    }
}
