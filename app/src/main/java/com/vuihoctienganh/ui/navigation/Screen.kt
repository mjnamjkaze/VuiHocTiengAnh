package com.vuihoctienganh.ui.navigation

sealed class Screen(val route: String) {
    data object Onboarding : Screen("onboarding")
    data object Home : Screen("home")
    data object Learn : Screen("learn")
    data object Quiz : Screen("quiz")
    data object Review : Screen("review")
    data object History : Screen("history")
    data object SourceSelect : Screen("source_select")
}
