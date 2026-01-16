package com.example.news_app.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.example.news_app.newsapp.presentation.nvgraph.NavGraph
import com.example.news_app.newsapp.presentation.onboarding.MainViewModel
import com.example.news_app.newsapp.ui.News_AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.splashCondition
            }
        }
        setContent {

            News_AppTheme {
//                val view = LocalView.current
//                val isDark = isSystemInDarkTheme()
//
//                SideEffect {
//                    val window = (view.context as Activity).window
//
//                    // Enable edge-to-edge
//                    WindowCompat.setDecorFitsSystemWindows(window, false)
//
//                    // Make bars transparent
//                    window.statusBarColor = Color.Transparent.toArgb()
//                    window.navigationBarColor = Color.Transparent.toArgb()
//
//                    // Control icon colors
//                    val controller = WindowCompat.getInsetsController(window, view)
//                    controller.isAppearanceLightStatusBars = !isDark
//                    controller.isAppearanceLightNavigationBars = !isDark
//                }
                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {
                    val startDestination = viewModel.startDestination
                    NavGraph(startDestination = startDestination)
                }
            }
        }
    }
}
//24222ad7cea14f0886b6d5c1194c6642

