package com.example.news_app.ui

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performTextInput
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.presentation.search.SearchEvent
import com.example.news_app.newsapp.presentation.search.SearchScreen
import com.example.news_app.newsapp.presentation.search.SearchState
import com.example.news_app.util.fakeArticles
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class SearchScreenTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private val pagingData = flowOf(PagingData.from(fakeArticles))

    @Test
    fun search_bar_is_displayed() {
        composeRule.setContent {
            SearchScreen(
                state = SearchState("", pagingData),
                event = {},
                navigateToDetails = {}
            )
        }

        composeRule.onNodeWithTag("search_bar")
            .assertIsDisplayed()
    }

    @Test
    fun clicking_article_triggers_navigation() {
        var clickedArticle: Article? = null

        composeRule.setContent {
            LazyColumn {
                items(fakeArticles) { article ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("article_test_tag_${article.url}")
                            .clickable { clickedArticle = article }
                    ) {
                        Text(article.title)
                    }
                }
            }
        }

        composeRule
            .onNodeWithTag("article_test_tag_${fakeArticles.first().url}")
            .assertExists()
            .assertIsDisplayed()
            .performClick()

        composeRule.runOnIdle {
            assertNotNull(clickedArticle)
            assertEquals(fakeArticles.first().url, clickedArticle?.url)
        }
    }


}