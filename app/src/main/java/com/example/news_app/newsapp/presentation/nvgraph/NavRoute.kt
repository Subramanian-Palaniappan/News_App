package com.example.news_app.newsapp.presentation.nvgraph

sealed class NavRoute(val route:String) {
    object OnBoardingScreen:NavRoute("OnBoardingScreen")
    object AppNavigationScreen:NavRoute("AppNavigationScreen")
    object NewsNavigation:NavRoute("NewsNavigation")
    object NewsNavigatorScreen:NavRoute("NewsNavigatorScreen")
    object HomeScreen:NavRoute("HomeScreen")
    object SearchScreen:NavRoute("SearchScreen")
    object DetailsScreen:NavRoute("DetailsScreen")
    object BookmarkScreen:NavRoute("BookmarkScreen")
}