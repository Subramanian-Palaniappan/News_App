package com.example.news_app.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.presentation.details.DetailsEvent
import com.example.news_app.newsapp.presentation.details.DetailsScreen
import com.example.news_app.util.fakeArticles
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class DetailsScreenTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()
    private val article: Article = fakeArticles.first()


    @Test
    fun display_articleContent() {
        composeRule.setContent {
            DetailsScreen(article, {}, {}, false)
        }
        composeRule.onNodeWithTag("article_title").assertExists().assertIsDisplayed()
        composeRule.onNodeWithTag("article_content").assertExists().assertIsDisplayed()
        composeRule.onNodeWithTag("article_image").assertExists()
        composeRule.onNodeWithTag("bookmark_button").assertExists()
        composeRule.onNodeWithTag("share_button").assertExists()
        composeRule.onNodeWithTag("browse_button").assertExists()
        composeRule.onNodeWithTag("back_button").assertExists()
    }

    @Test
    fun bookmark_event_triggered() {
        var triggered = false
        composeRule.setContent {
            DetailsScreen(
                article = article,
                event = { if (it is DetailsEvent.IsArticleBookmarked) triggered = true },
                navigateUp = {},
                isBookmark = false
            )
        }
        composeRule.onNodeWithTag("bookmark_button").performClick()
        composeRule.runOnIdle { assertTrue(triggered) }
    }

    @Test
    fun back_event_triggered() {
        var navigateToBack = false
        composeRule.setContent {
            DetailsScreen(
                article = article,
                event = {},
                navigateUp = { navigateToBack = true },
                isBookmark = false
            )
        }
        composeRule.onNodeWithTag("back_button").performClick()
        composeRule.runOnIdle {
            assertTrue(navigateToBack)
        }
    }

    @Test
    fun launchedEffect_IsBookmarkEvent() {
        var eventTriggered = false
        composeRule.setContent {
            DetailsScreen(
                article = article,
                event = { if (it is DetailsEvent.IsArticleBookmarked) eventTriggered = true },
                navigateUp = {},
                isBookmark = false
            )
        }
        composeRule.runOnIdle { assertTrue(eventTriggered) }
    }
}