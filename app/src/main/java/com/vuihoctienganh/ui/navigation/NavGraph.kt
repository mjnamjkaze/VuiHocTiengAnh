package com.vuihoctienganh.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                onStartLearn = { navController.navigate(Screen.Learn.route) },
                onStartReview = { navController.navigate(Screen.Review.route) },
                onOpenHistory = { navController.navigate(Screen.History.route) },
                onSelectSource = { navController.navigate(Screen.SourceSelect.route) }
            )
        }

        composable(Screen.Learn.route) {
            LearnScreen(
                repository = repository,
                audioEngine = audioEngine,
                onFinish = {
                    navController.navigate(Screen.Quiz.route) {
                        popUpTo(Screen.Learn.route) { inclusive = true }
                    }
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(Screen.Quiz.route) {
            QuizScreen(
                repository = repository,
                audioEngine = audioEngine,
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
                onBack = { navController.popBackStack() }
            )
        }
    }
}
