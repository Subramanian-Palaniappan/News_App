package com.example.news_app.newsapp.domain.usecases.news

import androidx.paging.PagingData
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(private val newsRepository: NewsRepository) {
    operator fun invoke(sources: List<String>, searchQuery: String): Flow<PagingData<Article>> {
        return newsRepository.searchNews(sources = sources, searchQuery = searchQuery)
    }
}