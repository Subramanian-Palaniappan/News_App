package com.example.news_app.ui

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.presentation.home.HomeScreen
import com.example.news_app.util.fakeArticles
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun homeScreen_displays_logo_and_searchBar() {
        composeRule.setContent {
            val pagingItems = flowOf(PagingData.from(fakeArticles))
                .collectAsLazyPagingItems()

            HomeScreen(
                article = pagingItems,
                navigateToSearch = {},
                navigateToDetails = {}
            )
        }

        composeRule.waitForIdle()

        composeRule.onNodeWithTag("app_logo").assertIsDisplayed()
        composeRule.onNodeWithTag("search_bar", useUnmergedTree = true)
            .assertIsDisplayed()
    }

    @Test
    fun searchBar_click_triggers_navigation() {
        var clicked = false
        composeRule.setContent {
            val pagingItems = flowOf(PagingData.from(fakeArticles)).collectAsLazyPagingItems()

            HomeScreen(
                article = pagingItems,
                navigateToSearch = { clicked = true },
                navigateToDetails = {})
        }
        composeRule.waitForIdle()

        composeRule.onNodeWithTag("search_bar").performClick()
        composeRule.runOnIdle { assertTrue(clicked) }
    }

    @Test
    fun marquee_text_shows_when_more_than_10_articles() {
        val manyArticles = List(12) {
            fakeArticles.first().copy(title = "News $it", url = "$it")
        }

        lateinit var pagingItems: LazyPagingItems<Article>

        composeRule.setContent {
            pagingItems = flowOf(PagingData.from(manyArticles)).collectAsLazyPagingItems()
            HomeScreen(article = pagingItems, navigateToSearch = {}, navigateToDetails = {})
        }

        // ✅ wait until marquee appears
        composeRule.waitUntil{
            pagingItems.itemSnapshotList.items.size >= 10
        }

        composeRule.onNodeWithTag("marquee_text").assertExists().assertIsDisplayed()
    }
}