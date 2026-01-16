package com.example.news_app.newsapp.presentation.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.news_app.R
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.presentation.news_navigator.navigateToTab
import com.example.news_app.newsapp.presentation.nvgraph.NavRoute
import com.example.news_app.newsapp.presentation.onboarding.Dimens.padding_24
import com.example.news_app.newsapp.presentation.onboarding.Dimens.smallPadding
import com.example.news_app.newsapp.presentation.onboarding.components.ArticleList

@Composable
fun BookmarkScreen(state: BookmarkState, navigateToDetails: (Article) -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = smallPadding, start = smallPadding, end = smallPadding)
    ) {
        Text(
            modifier = Modifier.testTag("bookmark_title"),
            text = "Bookmark",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(
                R.color.display_small
            )
        )
        Spacer(modifier = Modifier.height(padding_24))
        ArticleList(
            modifier = Modifier.testTag("bookmark_list"),
            articles = state.articles,
            onClick = { navigateToDetails(it) })
    }

}
