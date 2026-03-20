package com.vuihoctienganh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.navigation.compose.rememberNavController
import com.vuihoctienganh.engine.AudioEngine
import com.vuihoctienganh.ui.navigation.NavGraph
import com.vuihoctienganh.ui.theme.DarkBg
import com.vuihoctienganh.ui.theme.VuiHocTheme
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var audioEngine: AudioEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as VuiHocApp
        audioEngine = AudioEngine(this)

        setContent {
            VuiHocTheme {
                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                val onboardingCompleteKey = booleanPreferencesKey("onboarding_complete")
                val isFirstLaunch by context.dataStore.data
                    .map { !(it[onboardingCompleteKey] ?: false) }
                    .collectAsState(initial = null as Boolean?)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkBg
                ) {
                    if (isFirstLaunch != null) {
                        NavGraph(
                            navController = navController,
                            repository = app.repository,
                            audioEngine = audioEngine,
                            isFirstLaunch = isFirstLaunch!!
                        )
                    }
                }

                // Mark onboarding complete when navigating away from it
                LaunchedEffect(navController) {
                    navController.addOnDestinationChangedListener { _, destination, _ ->
                        if (destination.route != "onboarding") {
                            scope.launch {
                                context.dataStore.edit {
                                    it[onboardingCompleteKey] = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::audioEngine.isInitialized) {
            audioEngine.shutdown()
        }
    }
}
