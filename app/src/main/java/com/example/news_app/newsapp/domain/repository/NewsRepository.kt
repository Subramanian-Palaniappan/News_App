package com.example.news_app.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.news_app.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository{
    fun getNews(sources:List<String>) : Flow<PagingData<Article>>
    fun searchNews(searchQuery:String,sources:List<String>) : Flow<PagingData<Article>>
    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun getArticles():Flow<List<Article>>
    suspend fun getArticleFromUrl(articleUrl: String):Article?
}
