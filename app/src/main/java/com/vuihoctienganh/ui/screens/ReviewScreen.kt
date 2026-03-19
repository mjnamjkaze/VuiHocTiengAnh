package com.vuihoctienganh.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.vuihoctienganh.data.db.entity.UserWordEntity
import com.vuihoctienganh.data.db.entity.WordEntity
import com.vuihoctienganh.data.repository.WordRepository
import com.vuihoctienganh.engine.AudioEngine
import com.vuihoctienganh.engine.QuizEngine
import com.vuihoctienganh.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReviewScreen(
    repository: WordRepository,
    audioEngine: AudioEngine,
    onBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val reviewUserWords by repository.getReviewWords().collectAsState(initial = emptyList())
    var reviewWords by remember { mutableStateOf<List<Pair<UserWordEntity, WordEntity>>>(emptyList()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var showAnswer by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }
    var mode by remember { mutableStateOf("flashcard") } // flashcard or list

    LaunchedEffect(reviewUserWords) {
        val pairs = mutableListOf<Pair<UserWordEntity, WordEntity>>()
        for (uw in reviewUserWords) {
            val word = repository.getWordById(uw.wordId)
            if (word != null) {
                pairs.add(uw to word)
            }
        }
        reviewWords = pairs
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        TopAppBar(
            title = { Text("Ôn tập (${reviewWords.size})", color = TextPrimary) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, "back", tint = TextPrimary)
                }
            },
            actions = {
                TextButton(onClick = {
                    mode = if (mode == "flashcard") "list" else "flashcard"
                }) {
                    Text(
                        if (mode == "flashcard") "📋 Danh sách" else "🃏 Flashcard",
                        color = AccentTeal
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBg)
        )

        if (isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = AccentGreen)
            }
        } else if (reviewWords.isEmpty()) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🎉", fontSize = 64.sp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Không có từ cần ôn!", fontSize = 20.sp, color = TextPrimary, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Tất cả đã được ôn đúng lúc", fontSize = 16.sp, color = TextSecondary)
                }
            }
        } else if (mode == "flashcard" && currentIndex < reviewWords.size) {
            val (userWord, word) = reviewWords[currentIndex]
            val examples = QuizEngine.parseExamples(word.examples)

            // Progress
            LinearProgressIndicator(
                progress = { (currentIndex + 1f) / reviewWords.size },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .padding(horizontal = 20.dp),
                color = AccentTeal,
                trackColor = DarkCard
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "${currentIndex + 1} / ${reviewWords.size}",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                color = TextMuted
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Flashcard
            Card(
                onClick = { showAnswer = !showAnswer },
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
                    Text(word.word, fontSize = 36.sp, fontWeight = FontWeight.Bold, color = AccentTeal)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(word.ipa, fontSize = 16.sp, color = TextSecondary)

                    Spacer(modifier = Modifier.height(12.dp))
                    FilledTonalButton(
                        onClick = { audioEngine.speak(word.word) },
                        colors = ButtonDefaults.filledTonalButtonColors(containerColor = AccentTeal.copy(alpha = 0.15f))
                    ) {
                        Text("🔊 Phát âm", color = AccentTeal)
                    }

                    AnimatedVisibility(visible = showAnswer, enter = fadeIn() + expandVertically()) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            HorizontalDivider(color = DarkCardAlt, modifier = Modifier.padding(vertical = 16.dp))
                            Text(word.meaning, fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = AccentYellow)
                            Spacer(modifier = Modifier.height(12.dp))
                            examples.firstOrNull()?.let { ex ->
                                Text("💬 $ex", fontSize = 14.sp, color = TextSecondary, lineHeight = 20.sp)
                            }

                            // Stats
                            Spacer(modifier = Modifier.height(12.dp))
                            Row {
                                Text("✅ ${userWord.correctCount}", fontSize = 13.sp, color = CorrectGreen)
                                Spacer(modifier = Modifier.width(16.dp))
                                Text("❌ ${userWord.wrongCount}", fontSize = 13.sp, color = WrongRed)
                            }
                        }
                    }

                    if (!showAnswer) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text("Chạm để xem đáp án", fontSize = 14.sp, color = TextMuted)
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Review buttons
            if (showAnswer) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            scope.launch {
                                repository.updateAfterReview(word.id, false)
                                repository.updateDailyStats(wordsReviewed = 1)
                                nextReview(currentIndex, reviewWords.size, { currentIndex = it }, { showAnswer = false }, onBack)
                            }
                        },
                        modifier = Modifier.weight(1f).height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = WrongRed)
                    ) {
                        Text("😕 Chưa nhớ", fontSize = 15.sp)
                    }

                    Button(
                        onClick = {
                            scope.launch {
                                repository.updateAfterReview(word.id, true)
                                repository.updateDailyStats(wordsReviewed = 1)
                                nextReview(currentIndex, reviewWords.size, { currentIndex = it }, { showAnswer = false }, onBack)
                            }
                        },
                        modifier = Modifier.weight(1f).height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = AccentGreen),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("😊 Đã nhớ", fontSize = 15.sp, color = DarkBg)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

        } else {
            // List mode
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(reviewWords) { (userWord, word) ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = DarkCard),
                        shape = RoundedCornerShape(14.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { audioEngine.speak(word.word) }
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(word.word, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = AccentTeal)
                                Text(word.meaning, fontSize = 14.sp, color = TextSecondary)
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text("✅${userWord.correctCount} ❌${userWord.wrongCount}", fontSize = 12.sp, color = TextMuted)
                                Text("🔊", fontSize = 20.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun nextReview(
    current: Int,
    total: Int,
    setIndex: (Int) -> Unit,
    resetAnswer: () -> Unit,
    onBack: () -> Unit
) {
    if (current < total - 1) {
        setIndex(current + 1)
        resetAnswer()
    } else {
        onBack()
    }
}
