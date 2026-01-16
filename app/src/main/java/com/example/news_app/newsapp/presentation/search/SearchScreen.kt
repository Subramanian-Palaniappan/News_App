package com.example.news_app.newsapp.presentation.search

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.presentation.common.SearchBar
import com.example.news_app.newsapp.presentation.nvgraph.NavRoute
import com.example.news_app.newsapp.presentation.onboarding.Dimens.padding_24
import com.example.news_app.newsapp.presentation.onboarding.components.ArticleList

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(top = padding_24, start = padding_24, end = padding_24)
            .statusBarsPadding()
    ) {
        SearchBar(
            modifier = Modifier
                .testTag("search_bar")
                .focusable(),
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.updateSearchQuery(it)) },
            onSearch = { event(SearchEvent.searchNews) })
        Spacer(modifier = Modifier.height(padding_24))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticleList(
                articles = articles,
                onClick = { navigateToDetails(it) })
        }
    }
}