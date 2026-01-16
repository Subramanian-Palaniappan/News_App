package com.example.news_app.newsapp.data.remote.dto

import com.example.news_app.newsapp.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)