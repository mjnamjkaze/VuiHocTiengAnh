package com.vuihoctienganh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.navigation.compose.rememberNavController
import com.vuihoctienganh.engine.AudioEngine
import com.vuihoctienganh.ui.navigation.NavGraph
import com.vuihoctienganh.ui.theme.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    private lateinit var audioEngine: AudioEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val app = application as VuiHocApp
        audioEngine = AudioEngine(this)

        // Read onboarding state ONCE, synchronously, before rendering
        val onboardingCompleteKey = booleanPreferencesKey("onboarding_complete")
        val isFirstLaunch = runBlocking {
            dataStore.data.first().let { prefs ->
                !(prefs[onboardingCompleteKey] ?: false)
            }
        }

        setContent {
            VuiHocTheme {
                val context = LocalContext.current
                val scope = rememberCoroutineScope()
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkBg
                ) {
                    val prefs = LocalContext.current.getSharedPreferences("crash_prefs", android.content.Context.MODE_PRIVATE)
                    var lastCrash by remember { mutableStateOf(prefs.getString("last_crash", null)) }

                    if (lastCrash != null) {
                        // Display crash report
                        androidx.compose.foundation.layout.Column(
                            modifier = Modifier.fillMaxSize().padding(16.dp),
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                        ) {
                            androidx.compose.material3.Text("🚨 APP CRASHED!", color = WrongRed, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 24.sp)
                            androidx.compose.foundation.layout.Spacer(Modifier.height(16.dp))
                            // Scrollable text
                            androidx.compose.foundation.layout.Box(modifier = Modifier.weight(1f).background(DarkCard).padding(8.dp)) {
                                androidx.compose.foundation.lazy.LazyColumn { item { androidx.compose.material3.Text(lastCrash!!, color = TextPrimary, fontSize = 12.sp) } }
                            }
                            androidx.compose.foundation.layout.Spacer(Modifier.height(16.dp))
                            androidx.compose.foundation.layout.Row {
                                androidx.compose.material3.Button(onClick = {
                                    val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as android.content.ClipboardManager
                                    val clip = android.content.ClipData.newPlainText("Crash Log", lastCrash)
                                    clipboard.setPrimaryClip(clip)
                                    android.widget.Toast.makeText(context, "Đã copy lỗi!", android.widget.Toast.LENGTH_SHORT).show()
                                }, colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = AccentBlue)) {
                                    androidx.compose.material3.Text("Copy Lỗi", color = DarkBg)
                                }
                                androidx.compose.foundation.layout.Spacer(Modifier.width(16.dp))
                                androidx.compose.material3.Button(onClick = {
                                    prefs.edit().remove("last_crash").apply()
                                    lastCrash = null
                                }, colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = AccentGreen)) {
                                    androidx.compose.material3.Text("Clear & Continue", color = DarkBg)
                                }
                            }
                        }
                    } else {
                        NavGraph(
                            navController = navController,
                            repository = app.repository,
                            audioEngine = audioEngine,
                            isFirstLaunch = isFirstLaunch
                        )
                    }
                }

                // Mark onboarding complete when navigating away from it
                LaunchedEffect(Unit) {
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
