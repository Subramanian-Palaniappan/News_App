package com.example.news_app.newsapp.presentation.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news_app.R
import com.example.news_app.newsapp.presentation.home.HomeScreen
import com.example.news_app.newsapp.presentation.home.HomeViewModel
import com.example.news_app.newsapp.presentation.news_navigator.NewsNavigator
import com.example.news_app.newsapp.presentation.onboarding.OnBoardingScreen
import com.example.news_app.newsapp.presentation.onboarding.OnBoardingViewModel
import com.example.news_app.newsapp.presentation.search.SearchScreen
import com.example.news_app.newsapp.presentation.search.SearchViewModel

@Composable
fun NavGraph(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = NavRoute.AppNavigationScreen.route,
            startDestination = NavRoute.OnBoardingScreen.route
        ) {
            composable(route = NavRoute.OnBoardingScreen.route) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(event = viewModel::onEvent)


            }
        }

        navigation(route=NavRoute.NewsNavigation.route, startDestination = NavRoute.NewsNavigatorScreen.route){
            composable(route=NavRoute.NewsNavigatorScreen.route){
                NewsNavigator()
            }
        }
    }
}