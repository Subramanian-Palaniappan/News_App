package com.example.news_app.newsapp.presentation.details

import com.example.news_app.newsapp.domain.model.Article

sealed class DetailsEvent {
    data class InsertDeleteArticleDetail(val article: Article) : DetailsEvent()
    data class IsArticleBookmarked(val articleUrl: String) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
}