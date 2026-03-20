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
import com.vuihoctienganh.engine.*
import com.vuihoctienganh.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    repository: WordRepository,
    audioEngine: AudioEngine,
    onFinish: () -> Unit,
    onBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var questions by remember { mutableStateOf<List<QuizQuestion>>(emptyList()) }
    var currentIndex by remember { mutableIntStateOf(0) }
    var selectedAnswer by remember { mutableIntStateOf(-1) }
    var showResult by remember { mutableStateOf(false) }
    var correctCount by remember { mutableIntStateOf(0) }
    var totalAnswered by remember { mutableIntStateOf(0) }
    var showSummary by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val levels = listOf("A1", "A2")
        val words = repository.getDailyNewWords(WordSource.COCA, levels, 5)
        if (words.isEmpty()) {
            showSummary = true
            isLoading = false
            return@LaunchedEffect
        }

        val quizQuestions = mutableListOf<QuizQuestion>()
        for (word in words) {
            val distractors = repository.getRandomDistractors(WordSource.COCA, levels, word.id, 3)
            val type = QuizType.entries.random()
            quizQuestions.add(QuizEngine.generateQuestion(word, distractors, type))
        }
        questions = quizQuestions
        isLoading = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        TopAppBar(
            title = { Text("Trắc nghiệm", color = TextPrimary) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, "back", tint = TextPrimary)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBg)
        )

        when {
            isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = AccentGreen)
                }
            }
            showSummary -> {
                // Score summary
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp),
                        colors = CardDefaults.cardColors(containerColor = DarkCard),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val emoji = when {
                                totalAnswered == 0 -> "📝"
                                correctCount == totalAnswered -> "🏆"
                                correctCount >= totalAnswered * 0.8 -> "🌟"
                                correctCount >= totalAnswered * 0.6 -> "👍"
                                else -> "💪"
                            }
                            Text(emoji, fontSize = 64.sp)
                            Spacer(modifier = Modifier.height(16.dp))

                            if (totalAnswered == 0) {
                                Text("Chưa có từ để quiz", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                Text("Hãy học từ mới trước nhé!", fontSize = 16.sp, color = TextSecondary)
                            } else {
                                Text("Kết quả", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    "$correctCount / $totalAnswered",
                                    fontSize = 48.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (correctCount >= totalAnswered * 0.8) AccentGreen else AccentOrange
                                )
                                Text("câu đúng", fontSize = 16.sp, color = TextSecondary)

                                val percentage = if (totalAnswered > 0) (correctCount * 100 / totalAnswered) else 0
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    "Độ chính xác: $percentage%",
                                    fontSize = 16.sp,
                                    color = TextSecondary
                                )
                            }

                            Spacer(modifier = Modifier.height(24.dp))
                            Button(
                                onClick = onFinish,
                                colors = ButtonDefaults.buttonColors(containerColor = AccentGreen),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.fillMaxWidth().height(52.dp)
                            ) {
                                Text("Về trang chủ", color = DarkBg, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
            else -> {
                val question = questions[currentIndex]

                // Progress
                LinearProgressIndicator(
                    progress = (currentIndex + 1f) / questions.size,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                        .padding(horizontal = 20.dp),
                    color = AccentGreen,
                    trackColor = DarkCard
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    "${currentIndex + 1} / ${questions.size}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = TextMuted
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Quiz type badge
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = AccentPurple.copy(alpha = 0.15f),
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        question.type.displayName,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        fontSize = 13.sp,
                        color = AccentPurple
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Listen button for listen type
                if (question.type == QuizType.LISTEN) {
                    Button(
                        onClick = { audioEngine.speak(question.word.word) },
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .height(80.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = DarkCard),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("🔊  Nghe phát âm", fontSize = 24.sp, color = AccentTeal)
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Question
                Text(
                    question.question,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary,
                    lineHeight = 28.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Options
                question.options.forEachIndexed { index, option ->
                    val isSelected = selectedAnswer == index
                    val isCorrect = index == question.correctIndex

                    val containerColor = when {
                        !showResult -> if (isSelected) AccentBlue.copy(alpha = 0.2f) else DarkCard
                        isCorrect -> CorrectGreen.copy(alpha = 0.2f)
                        isSelected && !isCorrect -> WrongRed.copy(alpha = 0.2f)
                        else -> DarkCard
                    }

                    val borderColor = when {
                        !showResult && isSelected -> AccentBlue
                        showResult && isCorrect -> CorrectGreen
                        showResult && isSelected && !isCorrect -> WrongRed
                        else -> DarkCard
                    }

                    Card(
                        onClick = {
                            if (!showResult) {
                                selectedAnswer = index
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = containerColor),
                        shape = RoundedCornerShape(14.dp),
                        enabled = !showResult
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val letter = ('A' + index).toString()
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = borderColor.copy(alpha = 0.3f),
                                modifier = Modifier.size(32.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(letter, fontWeight = FontWeight.Bold, color = borderColor, fontSize = 14.sp)
                                }
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                option,
                                fontSize = 16.sp,
                                color = TextPrimary
                            )
                            if (showResult && isCorrect) {
                                Spacer(modifier = Modifier.weight(1f))
                                Text("✅", fontSize = 18.sp)
                            }
                            if (showResult && isSelected && !isCorrect) {
                                Spacer(modifier = Modifier.weight(1f))
                                Text("❌", fontSize = 18.sp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Submit / Next button
                Button(
                    onClick = {
                        if (!showResult && selectedAnswer >= 0) {
                            showResult = true
                            val isCorrect = selectedAnswer == question.correctIndex
                            totalAnswered++
                            if (isCorrect) correctCount++

                            scope.launch {
                                repository.saveQuizResult(
                                    wordId = question.word.id,
                                    quizType = question.type.name,
                                    answer = question.options[selectedAnswer],
                                    isCorrect = isCorrect
                                )
                                repository.updateAfterReview(question.word.id, isCorrect)
                                repository.updateDailyStats(quizCorrect = if (isCorrect) 1 else 0, quizTotal = 1)
                            }
                        } else if (showResult) {
                            if (currentIndex < questions.size - 1) {
                                currentIndex++
                                selectedAnswer = -1
                                showResult = false
                            } else {
                                showSummary = true
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (showResult) AccentTeal else AccentGreen
                    ),
                    shape = RoundedCornerShape(16.dp),
                    enabled = selectedAnswer >= 0
                ) {
                    Text(
                        if (showResult) {
                            if (currentIndex < questions.size - 1) "Câu tiếp theo →" else "Xem kết quả"
                        } else "Kiểm tra",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBg
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}
