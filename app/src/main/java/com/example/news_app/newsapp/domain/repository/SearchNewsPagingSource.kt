package com.example.news_app.newsapp.domain.repository

import androidx.paging.PagingDataEvent
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.news_app.newsapp.data.remote.dto.NewsApi
import com.example.news_app.newsapp.domain.model.Article

class SearchNewsPagingSource(
    private val newsApi: NewsApi,
    private val searchQuery: String,
    private val source: String
) : PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        return try {
            val searchNewsResponse =
                newsApi.searchNews(searchQuery = searchQuery, page = page, source = source)
            totalNewsCount += searchNewsResponse.articles.size
            val articles = searchNewsResponse.articles.distinctBy { it.title }
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == searchNewsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}