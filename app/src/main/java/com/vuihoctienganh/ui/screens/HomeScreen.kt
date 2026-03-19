package com.vuihoctienganh.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vuihoctienganh.data.repository.WordRepository
import com.vuihoctienganh.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    repository: WordRepository,
    onStartLearn: () -> Unit,
    onStartReview: () -> Unit,
    onOpenHistory: () -> Unit,
    onSelectSource: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val reviewCount by repository.getReviewCount().collectAsState(initial = 0)
    val masteredCount by repository.getMasteredCount().collectAsState(initial = 0)
    val learnedCount by repository.getLearnedCount().collectAsState(initial = 0)
    var streak by remember { mutableIntStateOf(0) }
    val recentStats by repository.getRecentStats(7).collectAsState(initial = emptyList())

    val todayStats = recentStats.firstOrNull()
    val todayLearned = todayStats?.wordsLearned ?: 0

    LaunchedEffect(Unit) {
        streak = repository.getStreak()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    "Xin chào! 👋",
                    fontSize = 16.sp,
                    color = TextSecondary
                )
                Text(
                    "Hôm nay học gì nào?",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            }

            // Streak badge
            if (streak > 0) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = StreakOrange.copy(alpha = 0.15f),
                    modifier = Modifier.padding(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("🔥", fontSize = 20.sp)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            "$streak",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = StreakOrange
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Progress ring card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = DarkCard),
            shape = RoundedCornerShape(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Progress ring
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(100.dp)
                ) {
                    val progress = (todayLearned / 5f).coerceAtMost(1f)
                    val animatedProgress by animateFloatAsState(
                        targetValue = progress,
                        animationSpec = tween(1000),
                        label = "progress"
                    )

                    Canvas(modifier = Modifier.size(100.dp)) {
                        // Background circle
                        drawArc(
                            color = DarkCardAlt,
                            startAngle = -90f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(width = 10f, cap = StrokeCap.Round)
                        )
                        // Progress arc
                        drawArc(
                            brush = Brush.sweepGradient(listOf(AccentGreen, AccentTeal)),
                            startAngle = -90f,
                            sweepAngle = 360f * animatedProgress,
                            useCenter = false,
                            style = Stroke(width = 10f, cap = StrokeCap.Round)
                        )
                    }

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            "$todayLearned/5",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text("từ hôm nay", fontSize = 11.sp, color = TextSecondary)
                    }
                }

                Spacer(modifier = Modifier.width(24.dp))

                // Stats
                Column {
                    StatRow("📚", "Đã học", "$learnedCount từ")
                    Spacer(modifier = Modifier.height(8.dp))
                    StatRow("⭐", "Thuộc lòng", "$masteredCount từ")
                    Spacer(modifier = Modifier.height(8.dp))
                    StatRow("🔄", "Cần ôn", "$reviewCount từ")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActionCard(
                modifier = Modifier.weight(1f),
                title = "Học từ mới",
                subtitle = "5 từ/ngày",
                emoji = "📖",
                gradientColors = listOf(AccentGreen, Teal400),
                onClick = onStartLearn,
                enabled = todayLearned < 5
            )
            ActionCard(
                modifier = Modifier.weight(1f),
                title = "Ôn tập",
                subtitle = "$reviewCount từ chờ",
                emoji = "🔄",
                gradientColors = listOf(AccentBlue, AccentPurple),
                onClick = onStartReview,
                enabled = reviewCount > 0
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ActionCard(
                modifier = Modifier.weight(1f),
                title = "Lịch sử",
                subtitle = "Tiến trình",
                emoji = "📊",
                gradientColors = listOf(AccentOrange, AccentPink),
                onClick = onOpenHistory
            )
            ActionCard(
                modifier = Modifier.weight(1f),
                title = "Nguồn từ",
                subtitle = "Chọn bộ từ",
                emoji = "📦",
                gradientColors = listOf(AccentPurple, AccentBlue),
                onClick = onSelectSource
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Weekly streak bar
        if (recentStats.isNotEmpty()) {
            Text(
                "Tuần này",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(12.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = DarkCard),
                shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val days = listOf("T2", "T3", "T4", "T5", "T6", "T7", "CN")
                    days.forEachIndexed { index, day ->
                        val stat = recentStats.getOrNull(6 - index)
                        val learned = stat?.wordsLearned ?: 0
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (learned > 0) AccentGreen.copy(alpha = 0.2f)
                                        else DarkCardAlt
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (learned > 0) {
                                    Text("✓", color = AccentGreen, fontWeight = FontWeight.Bold)
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(day, fontSize = 11.sp, color = TextMuted)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
private fun StatRow(emoji: String, label: String, value: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(emoji, fontSize = 16.sp)
        Spacer(modifier = Modifier.width(8.dp))
        Text(label, fontSize = 14.sp, color = TextSecondary)
        Spacer(modifier = Modifier.width(8.dp))
        Text(value, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
    }
}

@Composable
private fun ActionCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    emoji: String,
    gradientColors: List<Color>,
    onClick: () -> Unit,
    enabled: Boolean = true
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(130.dp),
        colors = CardDefaults.cardColors(
            containerColor = DarkCard,
            disabledContainerColor = DarkCard.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(20.dp),
        enabled = enabled
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(emoji, fontSize = 32.sp)
                Column {
                    Text(
                        title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (enabled) gradientColors.first() else TextMuted
                    )
                    Text(
                        subtitle,
                        fontSize = 12.sp,
                        color = TextSecondary
                    )
                }
            }
        }
    }
}
