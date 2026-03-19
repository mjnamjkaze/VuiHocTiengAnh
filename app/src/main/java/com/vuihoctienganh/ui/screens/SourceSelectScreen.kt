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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vuihoctienganh.data.source.WordSource
import com.vuihoctienganh.ui.theme.*
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.vuihoctienganh.dataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SourceSelectScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val selectedSourceKey = stringPreferencesKey("selected_source")
    val currentSource by context.dataStore.data
        .map { it[selectedSourceKey] ?: WordSource.COCA.name }
        .collectAsState(initial = WordSource.COCA.name)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        TopAppBar(
            title = { Text("Chọn nguồn từ vựng", color = TextPrimary) },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, "back", tint = TextPrimary)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = DarkBg)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            "Chọn bộ từ vựng phù hợp với mục tiêu học của bạn",
            modifier = Modifier.padding(horizontal = 20.dp),
            fontSize = 15.sp,
            color = TextSecondary,
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(WordSource.entries.toList()) { source ->
                val isSelected = currentSource == source.name
                val wordCount = when (source) {
                    WordSource.COCA -> "100 từ"
                    WordSource.BNC -> "50 từ"
                    WordSource.SLANG -> "30 từ"
                    WordSource.IDIOM -> "30 thành ngữ"
                }
                val details = when (source) {
                    WordSource.COCA -> "Từ phổ biến nhất trong giao tiếp Mỹ. Phân level A1-B2. Phù hợp luyện nói hàng ngày."
                    WordSource.BNC -> "Từ vựng học thuật & công sở từ British National Corpus. Tốt cho TOEIC & IELTS."
                    WordSource.SLANG -> "Slang hiện đại Gen Z: lit, vibe, slay... Hiểu văn hóa pop Anh-Mỹ."
                    WordSource.IDIOM -> "Thành ngữ phổ biến: break the ice, piece of cake... Nâng cao kỹ năng giao tiếp."
                }

                Card(
                    onClick = {
                        scope.launch {
                            context.dataStore.edit { it[selectedSourceKey] = source.name }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) AccentGreen.copy(alpha = 0.1f) else DarkCard
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(source.emoji, fontSize = 32.sp)
                            Spacer(modifier = Modifier.width(14.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    source.displayName,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) AccentGreen else TextPrimary
                                )
                                Text(
                                    source.description,
                                    fontSize = 13.sp,
                                    color = TextSecondary
                                )
                            }
                            if (isSelected) {
                                Icon(Icons.Default.CheckCircle, "selected", tint = AccentGreen, modifier = Modifier.size(28.dp))
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            details,
                            fontSize = 14.sp,
                            color = TextSecondary,
                            lineHeight = 20.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = AccentPurple.copy(alpha = 0.15f)
                        ) {
                            Text(
                                "📦 $wordCount",
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                fontSize = 12.sp,
                                color = AccentPurple
                            )
                        }
                    }
                }
            }
        }
    }
}
