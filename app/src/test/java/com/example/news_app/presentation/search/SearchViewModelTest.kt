package com.example.news_app.presentation.search

import androidx.paging.PagingData
import com.example.news_app.domain.usecase.ArticleDiffCallback
import com.example.news_app.newsapp.domain.usecases.news.GetNews
import com.example.news_app.newsapp.domain.usecases.news.NewsUseCases
import com.example.news_app.newsapp.domain.usecases.news.SearchNews
import com.example.news_app.newsapp.presentation.search.SearchEvent
import com.example.news_app.newsapp.presentation.search.SearchViewModel
import com.example.news_app.util.MainDispatcherRule
import com.example.news_app.util.fakeArticles
import com.example.news_app.util.collectData
import com.example.news_app.util.collectFlowData
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val searchNews: SearchNews = mockk()
    private lateinit var searchUseCase: NewsUseCases
    private lateinit var viewModelTest: SearchViewModel

    @Before
    fun setup() {
        val pagingData = PagingData.from(listOf(fakeArticles.first()))
        every { searchNews.invoke(any(), "daughter") } returns flowOf(pagingData)
        searchUseCase = NewsUseCases(
            getNews = mockk(), searchNews = searchNews, mockk(), mockk(),
            mockk(), mockk()
        )
        viewModelTest = SearchViewModel(searchUseCase)
    }

    @Test
    fun onEvent_updateSearchQuery_emits_paging_data() = runTest {
        viewModelTest.onEvent(SearchEvent.updateSearchQuery("daughter"))
        assertEquals("daughter", viewModelTest.state.value.searchQuery)
    }

    @Test
    fun onEvent_searchNews_emits_paging_data() = runTest {
        viewModelTest.onEvent(SearchEvent.updateSearchQuery("daughter"))
        viewModelTest.onEvent(SearchEvent.searchNews)
        advanceUntilIdle()

        val pagingData = viewModelTest.state.value.articles
        assertNotNull(pagingData)
        val items = pagingData!!.collectFlowData(diffCallback = ArticleDiffCallback, testScope = this)

        assertEquals(1, items.size)
        assertEquals(fakeArticles.first().title, items.first().title)
    }
}