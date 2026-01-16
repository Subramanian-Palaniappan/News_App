package com.example.news_app.presentation.bookmark

import androidx.paging.PagingData
import com.example.news_app.newsapp.domain.usecases.news.GetArticles
import com.example.news_app.newsapp.domain.usecases.news.NewsUseCases
import com.example.news_app.newsapp.presentation.bookmark.BookmarkViewModel
import com.example.news_app.util.MainDispatcherRule
import com.example.news_app.util.fakeArticles
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BookmarkViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val getBookmarkArticles: GetArticles = mockk()
    private lateinit var getBookmarkArticlesUseCase: NewsUseCases
    private lateinit var viewModelTest: BookmarkViewModel

    @Before
    fun setup() {
        every { getBookmarkArticles.invoke() } returns flowOf(fakeArticles)
        getBookmarkArticlesUseCase = NewsUseCases(
            getNews = mockk(),
            searchNews = mockk(),
            insertArticle = mockk(),
            deleteArticle = mockk(),
            getArticles = getBookmarkArticles,
            getArticleFromUrl = mockk()
        )
        viewModelTest= BookmarkViewModel(getBookmarkArticlesUseCase)
    }

    @Test
    fun getArticles_returns_list_of_articles() = runTest {
        advanceUntilIdle()
        val result=viewModelTest.state.value.articles
        assertEquals(fakeArticles.size,result.size)
        assertEquals(fakeArticles.last().title,result.first().title)
    }
}