package com.example.news_app.newsapp.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news_app.R
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.presentation.bookmark.BookmarkScreen
import com.example.news_app.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.news_app.newsapp.presentation.details.DetailsEvent
import com.example.news_app.newsapp.presentation.details.DetailsScreen
import com.example.news_app.newsapp.presentation.details.DetailsViewModel
import com.example.news_app.newsapp.presentation.home.HomeScreen
import com.example.news_app.newsapp.presentation.home.HomeViewModel
import com.example.news_app.newsapp.presentation.news_navigator.component.BottomNavigationItem
import com.example.news_app.newsapp.presentation.news_navigator.component.NewsBottomNavigation
import com.example.news_app.newsapp.presentation.nvgraph.NavRoute
import com.example.news_app.newsapp.presentation.search.SearchScreen
import com.example.news_app.newsapp.presentation.search.SearchViewModel

@Composable
fun NewsNavigator() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(
                icon = R.drawable.ic_home, text = "Home"
            ), BottomNavigationItem(
                icon = R.drawable.ic_search, text = "Search"
            ), BottomNavigationItem(
                icon = R.drawable.ic_bookmark, text = "Bookmark"
            )
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }


    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            NavRoute.HomeScreen.route -> 0
            NavRoute.SearchScreen.route -> 1
            NavRoute.BookmarkScreen.route -> 2
            else -> 0
        }
    }


    val isBottomNavigationVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == NavRoute.HomeScreen.route || backStackState?.destination?.route == NavRoute.SearchScreen.route || backStackState?.destination?.route == NavRoute.BookmarkScreen.route
    }

    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        if (isBottomNavigationVisible) {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selected = selectedItem,
                onItemClick = { index ->
                    when (index) {
                        0 -> navigateToTab(navController, NavRoute.HomeScreen.route)
                        1 -> navigateToTab(navController, NavRoute.SearchScreen.route)
                        2 -> navigateToTab(navController, NavRoute.BookmarkScreen.route)
                    }
                })
        }
    }) {
        val bottomPadding = it.calculateBottomPadding()
        NavHost(
            navController = navController,
            startDestination = NavRoute.HomeScreen.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {
            composable(route = NavRoute.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val articles = viewModel.news.collectAsLazyPagingItems()
                HomeScreen(article = articles, navigateToSearch = {
                    navigateToTab(
                        navController = navController, route = NavRoute.SearchScreen.route
                    )
                }, navigateToDetails = { article ->
                    navigateToDetails(
                        navController = navController, article = article
                    )
                })
            }

            composable(route = NavRoute.SearchScreen.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(
                    state = state, navigateToDetails = { article ->
                        navigateToDetails(
                            navController = navController, article = article
                        )
                    }, event = viewModel::onEvent
                )
            }

            composable(route = NavRoute.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()
                val isBookmarked by viewModel.isBookMarked
                if (viewModel.sideEffect != null) {
                    Toast.makeText(LocalContext.current, viewModel.sideEffect, Toast.LENGTH_SHORT)
                        .show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    .let { article ->
                        if (article != null) {
                            DetailsScreen(article = article,
                                event = viewModel::onEvent,
                                navigateUp = { navController.navigateUp() }, isBookmark = isBookmarked)
                        }
                    }
            }

            composable(route = NavRoute.BookmarkScreen.route) {
                val viewModel: BookmarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookmarkScreen(state = state, navigateToDetails = { article ->
                    navigateToDetails(
                        navController = navController, article = article
                    )
                })
            }
        }
    }
}

fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { it ->
            popUpTo(it) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(route = NavRoute.DetailsScreen.route)
}