package com.example.news_app.data.local

import android.app.Application
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.news_app.newsapp.data.local.NewsDao
import com.example.news_app.newsapp.data.local.NewsRoomDataBase
import com.example.news_app.newsapp.data.local.SourceTypeConverter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.example.news_app.util.fakeArticles
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first

@RunWith(AndroidJUnit4::class)
@OptIn(ExperimentalCoroutinesApi::class)
class BookmarkDaoTest {

    private var dataBase: NewsRoomDataBase? = null
    private var newsDao: NewsDao? = null

    @Before
    fun setup() {
        dataBase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NewsRoomDataBase::class.java
        ).addTypeConverter(SourceTypeConverter())
            .allowMainThreadQueries().build()

        newsDao = dataBase?.newsDao
    }

    @After
    fun tearDown() {
        dataBase?.close()
        dataBase = null
        newsDao = null
    }

    @Test
    fun insertArticle_and_getArticles() = runBlocking {
        val article = fakeArticles.first()
        newsDao?.insertArticle(article)

        val result = newsDao?.getArticles()?.first() ?: emptyList()
        assertEquals(1, result.size)
        assertEquals(article.url, result[0].url)
    }

    @Test
    fun getArticleFromUrl_returns_correct_Article() = runBlocking {
        val article = fakeArticles.first()
        newsDao?.insertArticle(article)

        // Make sure you are querying by the correct field (url or urlToImage)
        val result = newsDao?.getArticleFromUrl(article.url) // <-- usually URL is the key
        assertNotNull(result)
        assertEquals(article.title, result?.title)
    }

    @Test
    fun deleteArticle_removes_article() = runBlocking {
        val article = fakeArticles.first()
        newsDao?.insertArticle(article)
        newsDao?.deleteArticle(article)

        val result = newsDao?.getArticles()?.first() ?: emptyList()
        assertTrue(result.isEmpty())
    }
}
