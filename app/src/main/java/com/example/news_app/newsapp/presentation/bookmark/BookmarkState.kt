package com.example.news_app.newsapp.presentation.bookmark

import com.example.news_app.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class BookmarkState(val articles: List<Article> = emptyList()) {

}