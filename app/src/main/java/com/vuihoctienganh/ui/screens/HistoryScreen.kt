package com.vuihoctienganh.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vuihoctienganh.data.db.entity.DailyStatsEntity
import com.vuihoctienganh.data.db.entity.UserWordEntity
import com.vuihoctienganh.data.repository.WordRepository
import com.vuihoctienganh.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    repository: WordRepository,
    onBack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val learnedCount by repository.getLearnedCount().collectAsState(initial = 0)
    val masteredCount by repository.getMasteredCount().collectAsState(initial = 0)
    val totalCorrect by repository.getTotalCorrect().collectAsState(initial = 0)
    val totalAttempts by repository.getTotalAttempts().collectAsState(initial = 0)
    val recentStats by repository.getRecentStats(30).collectAsState(initial = emptyList())
    val allProgress by repository.getAllUserProgress().collectAsState(initial = emptyList())
    var streak by remember { mutableIntStateOf(0) }
    var selectedTab by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        streak = repository.getStreak()
    }

    val accuracy = if (totalAttempts > 0) (totalCorrect * 100 / totalAttempts) else 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        TopAppBar(
            title = { Text("Lịch sử & Tiến trình", color = TextPrimary) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, "back", tint = TextPrimary)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBg)
        )

        // Stats cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MiniStat(Modifier.weight(1f), "📚", "$learnedCount", "Đã học")
            MiniStat(Modifier.weight(1f), "⭐", "$masteredCount", "Thuộc lòng")
            MiniStat(Modifier.weight(1f), "🎯", "$accuracy%", "Chính xác")
            MiniStat(Modifier.weight(1f), "🔥", "$streak", "Streak")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs
        TabRow(
            selectedTabIndex = selectedTab,
            containerColor = DarkSurface,
            contentColor = AccentGreen,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Tab(selected = selectedTab == 0, onClick = { selectedTab = 0 }) {
                Text("📅 Hoạt động", modifier = Modifier.padding(12.dp), color = if (selectedTab == 0) AccentGreen else TextMuted)
            }
            Tab(selected = selectedTab == 1, onClick = { selectedTab = 1 }) {
                Text("📝 Từ vựng", modifier = Modifier.padding(12.dp), color = if (selectedTab == 1) AccentGreen else TextMuted)
            }
        }

        when (selectedTab) {
            0 -> {
                // Activity history
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (recentStats.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Chưa có hoạt động nào", color = TextMuted, fontSize = 16.sp)
                            }
                        }
                    }
                    items(recentStats) { stat ->
                        DailyStatCard(stat)
                    }
                }
            }
            1 -> {
                // Word list with status
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (allProgress.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("Chưa học từ nào", color = TextMuted, fontSize = 16.sp)
                            }
                        }
                    }
                    items(allProgress) { userWord ->
                        WordProgressCard(userWord)
                    }
                }
            }
        }
    }
}

@Composable
private fun MiniStat(modifier: Modifier, emoji: String, value: String, label: String) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(14.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(emoji, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(value, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Text(label, fontSize = 11.sp, color = TextMuted)
        }
    }
}

@Composable
private fun DailyStatCard(stat: DailyStatsEntity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(stat.date, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text("📖 ${stat.wordsLearned} học", fontSize = 12.sp, color = AccentGreen)
                    Spacer(modifier = Modifier.width(12.dp))
                    Text("🔄 ${stat.wordsReviewed} ôn", fontSize = 12.sp, color = AccentBlue)
                    Spacer(modifier = Modifier.width(12.dp))
                    val acc = if (stat.quizTotal > 0) (stat.quizCorrect * 100 / stat.quizTotal) else 0
                    Text("🎯 $acc%", fontSize = 12.sp, color = AccentOrange)
                }
            }
            if (stat.wordsLearned >= 5) {
                Text("⭐", fontSize = 24.sp)
            }
        }
    }
}

@Composable
private fun WordProgressCard(userWord: UserWordEntity) {
    val statusInfo = when (userWord.status) {
        "mastered" -> Triple("⭐", "Thuộc lòng", AccentYellow)
        "review" -> Triple("🔄", "Cần ôn", AccentBlue)
        "learning" -> Triple("📖", "Đang học", AccentGreen)
        else -> Triple("🆕", "Mới", TextMuted)
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = DarkCard),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(statusInfo.first, fontSize = 18.sp)
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text("Word #${userWord.wordId}", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
                Text(statusInfo.second, fontSize = 12.sp, color = statusInfo.third)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("✅ ${userWord.correctCount}  ❌ ${userWord.wrongCount}", fontSize = 12.sp, color = TextMuted)
                Text("Interval: ${userWord.interval}d", fontSize = 11.sp, color = TextMuted)
            }
        }
    }
}
