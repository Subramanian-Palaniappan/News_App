package com.example.news_app.data.repository

import com.example.news_app.newsapp.data.local.NewsDao
import com.example.news_app.newsapp.data.remote.dto.NewsApi
import com.example.news_app.newsapp.data.repository.NewsRepositoryImpl
import com.example.news_app.newsapp.domain.repository.NewsRepository
import com.example.news_app.util.fakeArticles
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryImplTest {
    private lateinit var repository: NewsRepository
    private val newsDao: NewsDao = mockk(relaxed = true)
    private val newsApi: NewsApi = mockk()

    @Before
    fun setup() {
        repository = NewsRepositoryImpl(newsApi, newsDao)
    }

    @Test
    fun getNews_returns_pagin_flow() = runTest {
        val result = repository.getNews(listOf("bbc-news"))
        assertNotNull(result)
    }

    @Test
    fun insertArticle_calls_dao_insert() = runTest {
        repository.insertArticle(fakeArticles.first())
        coVerify(exactly = 1) {
            newsDao.insertArticle(fakeArticles.first())
        }
    }

    @Test
    fun deleteArticle_calls_dao_delete() = runTest {
        repository.deleteArticle(fakeArticles.first())
        coVerify(exactly = 1) {
            newsDao.deleteArticle(fakeArticles.first())
        }
    }

    @Test
    fun getArticle_returns_article_from_dao() = runTest {
        val articles = listOf(fakeArticles.first())
        every {
            newsDao.getArticles()
        } returns flowOf(articles)

        val result = repository.getArticles().first()
        assertEquals(1, result.size)
        assertEquals(fakeArticles.first().title, result[0].title)
    }

    @Test
    fun getArticleFromUrl_returns_article_from_dao() = runTest {
        coEvery {
            newsDao.getArticleFromUrl(fakeArticles.first().url)
        } returns fakeArticles.first()
        val result = repository.getArticleFromUrl(fakeArticles.first().url)
        assertNotNull(result)
        assertEquals(fakeArticles.first().title, result?.title)
    }
}