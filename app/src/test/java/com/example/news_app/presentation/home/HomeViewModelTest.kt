package com.example.news_app.presentation.home

import androidx.paging.PagingData
import com.example.news_app.domain.usecase.ArticleDiffCallback
import com.example.news_app.newsapp.domain.usecases.news.GetNews
import com.example.news_app.newsapp.domain.usecases.news.NewsUseCases
import com.example.news_app.newsapp.presentation.home.HomeViewModel
import com.example.news_app.util.MainDispatcherRule
import com.example.news_app.util.collectData
import com.example.news_app.util.fakeArticles
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getNews: GetNews = mockk()
    private lateinit var newsUseCases: NewsUseCases
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        val pagingData = PagingData.from(listOf(fakeArticles.first()))
        every { getNews.invoke(any()) } returns flowOf(pagingData)
        newsUseCases = NewsUseCases(
            getNews = getNews,
            searchNews = mockk(),
            insertArticle = mockk(),
            deleteArticle = mockk(),
            getArticles = mockk(),
            getArticleFromUrl = mockk()
        )
        viewModel = HomeViewModel(newsUseCases)
    }

    @Test
    fun news_emits_paging_data() = runTest {
        val pagingData = viewModel.news.first()
        val items = pagingData.collectData(diffCallback = ArticleDiffCallback, testScope = this)
        assertEquals(1, items.size)
    }
}