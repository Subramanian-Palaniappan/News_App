package com.example.news_app.presentation.details

import androidx.paging.PagingData
import com.example.news_app.newsapp.domain.repository.NewsRepository
import com.example.news_app.newsapp.domain.usecases.news.GetArticleFromUrl
import com.example.news_app.newsapp.domain.usecases.news.NewsUseCases
import com.example.news_app.newsapp.presentation.details.DetailsViewModel
import com.example.news_app.util.MainDispatcherRule
import com.example.news_app.util.fakeArticles
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {
    private val repository: NewsRepository = mockk()
    private lateinit var getArticleFromUrl: GetArticleFromUrl

    @Before
    fun setup(){
        getArticleFromUrl=GetArticleFromUrl(repository)
    }

    @Test
    fun invoke_returns_article_from_repo() = runTest {
        val article = fakeArticles.first()
        coEvery { repository.getArticleFromUrl(article.url) } returns article
        val result = getArticleFromUrl(article.url)
        assertNotNull(result)
        assertEquals(article.title,result?.title)
        coVerify(exactly = 1) { repository.getArticleFromUrl(article.url) }
    }

}