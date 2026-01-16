package com.example.news_app.newsapp.domain.usecases.news

import com.example.news_app.newsapp.data.local.NewsDao
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.domain.repository.NewsRepository

class GetArticleFromUrl(private val repository: NewsRepository) {
    suspend operator fun invoke(articleUrl: String): Article? {
        return repository.getArticleFromUrl(articleUrl)
    }
}