package com.example.news_app.newsapp.domain.usecases.news

import com.example.news_app.newsapp.data.local.NewsDao
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetArticles(private val repository: NewsRepository) {
     operator fun invoke(): Flow<List<Article>> {
        return repository.getArticles()
    }
}