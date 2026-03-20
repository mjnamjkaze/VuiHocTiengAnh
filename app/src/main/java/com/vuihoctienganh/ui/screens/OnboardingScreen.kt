@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vuihoctienganh.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingScreen(onComplete: () -> Unit) {
    var currentStep by remember { mutableIntStateOf(0) }
    var selectedLevel by remember { mutableStateOf("") }
    var selectedGoal by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Progress indicator
            Spacer(modifier = Modifier.height(48.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .height(4.dp)
                            .weight(1f)
                            .padding(horizontal = 4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(
                                if (index <= currentStep) AccentGreen
                                else DarkCard
                            )
                    )
                }
            }

            Spacer(modifier = Modifier.height(48.dp))

            AnimatedContent(
                targetState = currentStep,
                transitionSpec = {
                    slideInHorizontally { it } + fadeIn() togetherWith
                            slideOutHorizontally { -it } + fadeOut()
                },
                label = "onboarding_step"
            ) { step ->
                when (step) {
                    0 -> WelcomeStep()
                    1 -> LevelStep(selectedLevel) { selectedLevel = it }
                    2 -> GoalStep(selectedGoal) { selectedGoal = it }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Next button
            Button(
                onClick = {
                    if (currentStep < 2) {
                        currentStep++
                    } else {
                        onComplete()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = AccentGreen),
                shape = RoundedCornerShape(16.dp),
                enabled = when (currentStep) {
                    1 -> selectedLevel.isNotEmpty()
                    2 -> selectedGoal.isNotEmpty()
                    else -> true
                }
            ) {
                Text(
                    text = if (currentStep < 2) "Tiếp tục" else "Bắt đầu học! 🚀",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBg
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
private fun WelcomeStep() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("🎓", fontSize = 72.sp)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            "Vui Học Tiếng Anh",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            "Học 5 từ mỗi ngày\nNhớ lâu với Spaced Repetition\nHoàn toàn offline",
            fontSize = 16.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )
    }
}

@Composable
private fun LevelStep(selected: String, onSelect: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("📊", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Trình độ của bạn?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(32.dp))

        OptionCard(
            title = "Người mới bắt đầu",
            subtitle = "A1 - A2 • Từ vựng cơ bản",
            emoji = "🌱",
            isSelected = selected == "beginner",
            onClick = { onSelect("beginner") }
        )
        Spacer(modifier = Modifier.height(12.dp))
        OptionCard(
            title = "Trung cấp",
            subtitle = "B1 - B2 • Từ vựng nâng cao",
            emoji = "🌿",
            isSelected = selected == "intermediate",
            onClick = { onSelect("intermediate") }
        )
    }
}

@Composable
private fun GoalStep(selected: String, onSelect: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("🎯", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Mục tiêu học?",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )
        Spacer(modifier = Modifier.height(32.dp))

        GoalOption("Giao tiếp hàng ngày", "💬", selected == "communication") { onSelect("communication") }
        Spacer(modifier = Modifier.height(12.dp))
        GoalOption("Luyện thi TOEIC", "📝", selected == "toeic") { onSelect("toeic") }
        Spacer(modifier = Modifier.height(12.dp))
        GoalOption("Cuộc sống hàng ngày", "🏠", selected == "daily") { onSelect("daily") }
    }
}

@Composable
private fun OptionCard(title: String, subtitle: String, emoji: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) AccentGreen.copy(alpha = 0.15f) else DarkCard
        ),
        border = if (isSelected) CardDefaults.outlinedCardBorder().copy(
            width = 2.dp,
            brush = Brush.linearGradient(listOf(AccentGreen, AccentTeal))
        ) else null,
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 32.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                Text(subtitle, fontSize = 14.sp, color = TextSecondary)
            }
            Spacer(modifier = Modifier.weight(1f))
            if (isSelected) {
                Icon(Icons.Default.CheckCircle, "selected", tint = AccentGreen)
            }
        }
    }
}

@Composable
private fun GoalOption(title: String, emoji: String, isSelected: Boolean, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) AccentGreen.copy(alpha = 0.15f) else DarkCard
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(emoji, fontSize = 28.sp)
            Spacer(modifier = Modifier.width(16.dp))
            Text(title, fontSize = 17.sp, fontWeight = FontWeight.Medium, color = TextPrimary)
            Spacer(modifier = Modifier.weight(1f))
            if (isSelected) {
                Icon(Icons.Default.CheckCircle, "selected", tint = AccentGreen)
            }
        }
    }
}
