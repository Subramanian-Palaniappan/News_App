package com.example.news_app.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.news_app.newsapp.presentation.bookmark.BookmarkScreen
import com.example.news_app.newsapp.presentation.bookmark.BookmarkState
import com.example.news_app.newsapp.presentation.details.DetailsScreen
import com.example.news_app.util.fakeArticles
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class BookmarkScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()
    private val fakeArticleList = fakeArticles

    @Test
    fun display_bookmark_title() {

        composeRule.setContent {
            BookmarkScreen(state = BookmarkState(fakeArticleList), navigateToDetails = {})
        }

        composeRule.onNodeWithTag("bookmark_title").assertIsDisplayed()
    }

    @Test
    fun display_list_of_articles() {
        var navigateToDetailsTriggered = false
        composeRule.setContent {
            BookmarkScreen(
                state = BookmarkState(fakeArticleList),
                navigateToDetails = { navigateToDetailsTriggered = true })
        }

        composeRule.onNodeWithTag("article_test_tag_${fakeArticleList.first().url}").performClick()
        composeRule.runOnIdle {
            assertTrue(navigateToDetailsTriggered)
        }
    }
}