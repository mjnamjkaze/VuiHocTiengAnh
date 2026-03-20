package com.vuihoctienganh.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vuihoctienganh.data.db.entity.WordEntity
import com.vuihoctienganh.data.repository.WordRepository
import com.vuihoctienganh.data.source.WordSource
import com.vuihoctienganh.engine.AudioEngine
import com.vuihoctienganh.engine.QuizEngine
import com.vuihoctienganh.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LearnScreen(
    repository: WordRepository,
    audioEngine: AudioEngine,
    topic: String? = null,
    onFinish: (List<Int>) -> Unit,
    onBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var words by remember { mutableStateOf<List<WordEntity>>(emptyList()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var showMeaning by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    val learnedWordIds = remember { mutableListOf<Int>() }

    LaunchedEffect(Unit) {
        try {
            val levels = listOf("A1", "A2") // TODO: based on user setting
            words = if (topic != null) {
                repository.getNewWordsByTopic(WordSource.COCA, levels, topic, 5)
            } else {
                repository.getDailyNewWords(WordSource.COCA, levels, 5)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            words = emptyList()
        }
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        // Top bar
        TopAppBar(
            title = { Text("Học từ mới", color = TextPrimary) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, "back", tint = TextPrimary)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBg)
        )

        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = AccentGreen)
            }
        } else if (words.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🎉", fontSize = 64.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Bạn đã học hết từ hôm nay!", fontSize = 20.sp, color = TextPrimary, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Quay lại ôn tập nhé", fontSize = 16.sp, color = TextSecondary)
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = onBack, colors = ButtonDefaults.buttonColors(containerColor = AccentGreen)) {
                        Text("Về trang chủ", color = DarkBg)
                    }
                }
            }
        } else if (currentIndex < words.size) {
            val word = words[currentIndex]
            val examples = QuizEngine.parseExamples(word.examples)

            // Progress bar
            LinearProgressIndicator(
                progress = (currentIndex + 1f) / words.size,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .padding(horizontal = 20.dp),
                color = AccentGreen,
                trackColor = DarkCard
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "${currentIndex + 1} / ${words.size}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = TextMuted
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Word card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCard),
                shape = RoundedCornerShape(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Word
                    Text(
                        word.word,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentGreen
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // IPA
                    Text(
                        word.ipa,
                        fontSize = 18.sp,
                        color = TextSecondary
                    )

                    // Part of speech
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = AccentBlue.copy(alpha = 0.15f),
                        modifier = Modifier.padding(vertical = 8.dp)
                    ) {
                        Text(
                            word.partOfSpeech,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            fontSize = 13.sp,
                            color = AccentBlue
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Audio button
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        FilledTonalButton(
                            onClick = { audioEngine.speak(word.word) },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = AccentTeal.copy(alpha = 0.15f)
                            )
                        ) {
                            Text("🔊 Phát âm", color = AccentTeal)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        FilledTonalButton(
                            onClick = { audioEngine.speakSlow(word.word) },
                            colors = ButtonDefaults.filledTonalButtonColors(
                                containerColor = AccentOrange.copy(alpha = 0.15f)
                            )
                        ) {
                            Text("🐢 Chậm", color = AccentOrange)
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Meaning (reveal)
                    AnimatedVisibility(
                        visible = showMeaning,
                        enter = fadeIn() + expandVertically()
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Divider(color = DarkCardAlt, modifier = Modifier.padding(vertical = 12.dp))

                            Text(
                                word.meaning,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = AccentYellow
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Examples
                            examples.forEachIndexed { idx, example ->
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                    colors = CardDefaults.cardColors(containerColor = DarkCardAlt),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(12.dp),
                                        verticalAlignment = Alignment.Top
                                    ) {
                                        Text("💬", fontSize = 14.sp)
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            example,
                                            fontSize = 14.sp,
                                            color = TextSecondary,
                                            lineHeight = 20.sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    if (!showMeaning) {
                        Spacer(modifier = Modifier.height(16.dp))
                        TextButton(onClick = { showMeaning = true }) {
                            Text("👀 Xem nghĩa", color = AccentTeal, fontSize = 16.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Bottom buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Don't know
                OutlinedButton(
                    onClick = {
                        scope.launch {
                            repository.markWordUnknown(word.id)
                            repository.updateDailyStats(wordsLearned = 1)
                            learnedWordIds.add(word.id)
                            moveToNext(currentIndex, words.size, { currentIndex = it }, { showMeaning = false }) { onFinish(learnedWordIds) }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = WrongRed)
                ) {
                    Text("❌ Chưa biết", fontSize = 16.sp)
                }

                // Know
                Button(
                    onClick = {
                        scope.launch {
                            repository.markWordKnown(word.id)
                            repository.updateDailyStats(wordsLearned = 1)
                            learnedWordIds.add(word.id)
                            moveToNext(currentIndex, words.size, { currentIndex = it }, { showMeaning = false }) { onFinish(learnedWordIds) }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = AccentGreen),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("✅ Đã biết", fontSize = 16.sp, color = DarkBg)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private fun moveToNext(
    currentIndex: Int,
    total: Int,
    setIndex: (Int) -> Unit,
    resetMeaning: () -> Unit,
    onFinish: () -> Unit
) {
    if (currentIndex < total - 1) {
        setIndex(currentIndex + 1)
        resetMeaning()
    } else {
        onFinish()
    }
}
