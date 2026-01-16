package com.example.news_app.domain.usecase

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.DiffUtil
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.domain.repository.NewsRepository
import com.example.news_app.newsapp.domain.usecases.news.GetNews
import com.example.news_app.util.MainDispatcherRule
import com.example.news_app.util.NoopListUpdateCallback
import com.example.news_app.util.fakeArticles
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GetNewsTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val repository: NewsRepository = mockk()
    private lateinit var getNews: GetNews

    @Before
    fun setup() {
        getNews = GetNews(repository)
    }

    @Test
    fun `invoke returns paging data repository`() = runTest {
        val sources = listOf("bbc-news", "abc-news")

        val pagingData = PagingData.from(fakeArticles)
        val flow = flowOf(pagingData)
        coEvery { repository.getNews(sources) } returns flow

        val result = getNews(sources).first()

        val differ = AsyncPagingDataDiffer(
            diffCallback = ArticleDiffCallback,
            updateCallback = NoopListUpdateCallback,
            workerDispatcher = Dispatchers.Main
        )

        differ.submitData(result)
        advanceUntilIdle()

        assertEquals(fakeArticles.size, differ.snapshot().size)
        assertEquals(fakeArticles[0].title, differ.snapshot()[0]?.title)
        coVerify(exactly = 1) {
            repository.getNews(sources)
        }
    }
}

object ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article) = oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: Article, newItem: Article) = oldItem == newItem
}