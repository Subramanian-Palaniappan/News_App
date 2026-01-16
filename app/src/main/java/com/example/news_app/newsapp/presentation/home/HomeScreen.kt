package com.example.news_app.newsapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.news_app.R
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.presentation.common.SearchBar
import com.example.news_app.newsapp.presentation.nvgraph.NavRoute
import com.example.news_app.newsapp.presentation.onboarding.Dimens.padding_24
import com.example.news_app.newsapp.presentation.onboarding.Dimens.padding_30
import com.example.news_app.newsapp.presentation.onboarding.components.ArticleList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    article: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    val title by remember {
        derivedStateOf {
            val items = article.itemSnapshotList.items
            if (items.size >= 10) {
                items.take(10)
                    .joinToString(separator =  " 🔵 ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = padding_24)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "app_logo",
            modifier = Modifier
                .testTag("app_logo")
                .width(150.dp)
                .height(
                    padding_30
                )
                .padding(horizontal = padding_30)
        )
        Spacer(modifier = Modifier.height(padding_24))
        SearchBar(
            modifier = Modifier
                .testTag("search_bar")
                .padding(padding_24),
            text = "",
            readOnly = true,
            onClick = { navigateToSearch() },
            onValueChange = {},
            onSearch = {})
        Spacer(modifier = Modifier.height(padding_24))
        if (title.isNotEmpty()) {
            Text(
                text = title,
                modifier = Modifier
                    .testTag("marquee_text")
                    .fillMaxWidth()
                    .padding(start = padding_24)
                    .basicMarquee(),
                fontSize = 12.sp,
                color = colorResource(id = R.color.placeholder)
            )
        }
        Spacer(modifier = Modifier.height(padding_24))
        ArticleList(
            modifier = Modifier.padding(horizontal = padding_24),
            articles = article,
            onClick = { navigateToDetails(it) })
    }
}