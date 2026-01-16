package com.example.news_app.newsapp.presentation.details

import android.content.Intent
import android.net.Uri
import android.telecom.Call.Details
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.news_app.R
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.presentation.details.components.DetailsTopBar
import com.example.news_app.newsapp.presentation.onboarding.Dimens.smallPadding

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUp: () -> Unit,
    isBookmark: Boolean
) {
    val context = LocalContext.current
    LaunchedEffect(article.url) {
        event(DetailsEvent.IsArticleBookmarked(article.url))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(smallPadding)
    ) {
        DetailsTopBar(
            isBookmarked = isBookmark,
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "plain/text"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBackClick = navigateUp,
            onBookmarkClick = { event(DetailsEvent.InsertDeleteArticleDetail(article)) },
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data =
                        Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            })
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = smallPadding,
                top = smallPadding,
                end = smallPadding
            )
        )
        {
            item {
                AsyncImage(
                    modifier = Modifier
                        .testTag("article_image")
                        .fillMaxWidth()
                        .fillMaxHeight(fraction = 0.4f),
                    model = ImageRequest.Builder(context = context).data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(smallPadding))
                Text(
                    modifier = Modifier.testTag("article_title"),
                    text = article.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.text_title)
                )
                Spacer(modifier = Modifier.width(smallPadding))
                Text(
                    modifier = Modifier.testTag("article_content"),
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(id = R.color.display_small)
                )
            }
        }
    }
}

//@Preview(showBackground = true, device = Devices.NEXUS_7, apiLevel = 33)
//@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES, device = Devices.NEXUS_7, apiLevel = 33)
//@Composable
//fun DetailsScreenPreview() {
//    News_AppTheme {
//        DetailsScreen(
//            article = Article(
//                author = "",
//                content = "adjkbajksjoin jnioasnjibsajn jknasjionjiasnj asjkn as",
//                description = "sdknkjdjind jkansjasj ns ",
//                publishedAt = "",
//                source = Source(id = "12", name = "as"),
//                title = "test title",
//                urlToImage = "https://i.abcnewsfe.com/a/f4475f30-1aa8-4e8a-9966-5f25baa00885/atm-gty-er-240117_1705508290923_hpMain_16x9.jpg?w=1600",
//                url = "https://abcnews.go.com/US/scammers-notched-333-million-bitcoin-atm-scams-2025/story?id=128526877"
//            ), event = {}, navigateUp = {},
//        )
//    }
//}