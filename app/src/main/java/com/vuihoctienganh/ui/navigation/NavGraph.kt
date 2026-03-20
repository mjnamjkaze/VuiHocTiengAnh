package com.vuihoctienganh.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vuihoctienganh.data.repository.WordRepository
import com.vuihoctienganh.engine.AudioEngine
import com.vuihoctienganh.ui.screens.*

@Composable
fun NavGraph(
    navController: NavHostController,
    repository: WordRepository,
    audioEngine: AudioEngine,
    isFirstLaunch: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = if (isFirstLaunch) Screen.Onboarding.route else Screen.Home.route
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onComplete = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                repository = repository,
                onStartLearn = { topic -> 
                    val route = if (topic != null) "${Screen.Learn.route}?topic=$topic" else Screen.Learn.route
                    navController.navigate(route) 
                },
                onStartReview = { navController.navigate(Screen.Review.route) },
                onOpenHistory = { navController.navigate(Screen.History.route) },
                onSelectSource = { navController.navigate(Screen.SourceSelect.route) }
            )
        }

        composable(
            route = "${Screen.Learn.route}?topic={topic}",
            arguments = listOf(navArgument("topic") { type = NavType.StringType; nullable = true })
        ) { backStackEntry ->
            val topic = backStackEntry.arguments?.getString("topic")
            LearnScreen(
                repository = repository,
                audioEngine = audioEngine,
                topic = topic,
                onFinish = { learnedWordIds ->
                    val idsParam = learnedWordIds.joinToString(",")
                    navController.navigate("quiz?wordIds=$idsParam") {
                        popUpTo(Screen.Learn.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "quiz?wordIds={wordIds}",
            arguments = listOf(navArgument("wordIds") { type = NavType.StringType; defaultValue = "" })
        ) { backStackEntry ->
            val wordIdsStr = backStackEntry.arguments?.getString("wordIds") ?: ""
            val wordIds = if (wordIdsStr.isNotEmpty()) {
                wordIdsStr.split(",").mapNotNull { it.toIntOrNull() }
            } else {
                emptyList()
            }
            QuizScreen(
                repository = repository,
                audioEngine = audioEngine,
                learnedWordIds = wordIds,
                onFinish = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Review.route) {
            ReviewScreen(
                repository = repository,
                audioEngine = audioEngine,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.History.route) {
            HistoryScreen(
                repository = repository,
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.SourceSelect.route) {
            SourceSelectScreen(
                repository = repository,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
