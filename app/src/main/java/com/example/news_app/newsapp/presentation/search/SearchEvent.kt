package com.example.news_app.newsapp.presentation.search

sealed class SearchEvent {
    data class updateSearchQuery(val searchQuery:String):SearchEvent()
    object searchNews:SearchEvent()
}