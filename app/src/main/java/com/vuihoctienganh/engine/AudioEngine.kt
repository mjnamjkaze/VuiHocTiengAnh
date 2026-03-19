package com.vuihoctienganh.engine

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

/**
 * Audio engine using Android TextToSpeech for offline pronunciation.
 */
class AudioEngine(context: Context) {
    private var tts: TextToSpeech? = null
    private var isReady = false

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
                tts?.setSpeechRate(0.85f)
                isReady = true
            }
        }
    }

    fun speak(text: String) {
        if (isReady) {
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, text.hashCode().toString())
        }
    }

    fun speakSlow(text: String) {
        if (isReady) {
            tts?.setSpeechRate(0.55f)
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, text.hashCode().toString())
            // restore normal speed after
            tts?.setSpeechRate(0.85f)
        }
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
