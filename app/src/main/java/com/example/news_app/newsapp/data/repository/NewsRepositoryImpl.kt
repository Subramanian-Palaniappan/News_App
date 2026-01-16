package com.example.news_app.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.news_app.newsapp.data.local.NewsDao
import com.example.news_app.newsapp.data.remote.dto.NewsApi
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.domain.repository.NewsPagingSource
import com.example.news_app.newsapp.domain.repository.NewsRepository
import com.example.news_app.newsapp.domain.repository.SearchNewsPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImpl(private val newsApi: NewsApi, private val newsDao: NewsDao) :
    NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(newsApi = newsApi, source = sources.joinToString(separator = ","))
            }).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    newsApi = newsApi,
                    searchQuery = searchQuery,
                    source = sources.joinToString(separator = ",")
                )
            }).flow
    }

    override suspend fun insertArticle(article: Article) {
        return newsDao.insertArticle(article)
    }

    override suspend fun deleteArticle(article: Article) {
        return newsDao.deleteArticle(article)
    }

    override fun getArticles(): Flow<List<Article>> {
        return newsDao.getArticles().onEach { it }
    }

    override suspend fun getArticleFromUrl(articleUrl: String): Article? {
        return newsDao.getArticleFromUrl(articleUrl = articleUrl)
    }


}