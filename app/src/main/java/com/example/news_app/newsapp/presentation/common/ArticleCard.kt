package com.example.news_app.newsapp.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.news_app.R
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.domain.model.Source
import com.example.news_app.newsapp.presentation.onboarding.Dimens.articleCardSize
import com.example.news_app.newsapp.presentation.onboarding.Dimens.extraSmallPadding
import com.example.news_app.newsapp.presentation.onboarding.Dimens.smallIconSize
import com.example.news_app.newsapp.ui.News_AppTheme

@Composable
fun ArticleCard(modifier: Modifier = Modifier, article: Article, onClick: () -> Unit) {
    val context = LocalContext.current
    Row(modifier = modifier
        .testTag("article_test_tag_${article.url}")
        .clickable { onClick() }) {
        AsyncImage(
            modifier = Modifier
                .size(articleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context = context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(extraSmallPadding)
                .height(
                    articleCardSize
                )
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = colorResource(
                    R.color.text_title
                )
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(R.color.body)
                )
                Spacer(modifier = Modifier.width(extraSmallPadding))
                Icon(
                    painter = painterResource(R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(
                        smallIconSize
                    ),
                    tint = colorResource(R.color.body)
                )
                Spacer(modifier = Modifier.width(extraSmallPadding))
                Text(
                    text = article.publishedAt.timeAgo(),
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(R.color.body)
                )
            }
        }
    }
}

@Preview(
    showBackground = true, device = Devices.PIXEL_7,
    apiLevel = 33
)
@Preview(
    uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_7,
    apiLevel = 33
)
@Composable
fun ArticleCardPreview() {
    News_AppTheme {
        ArticleCard(
            article = Article(
                author = "",
                content = "",
                description = "",
                publishedAt = "2 hours",
                source = Source(id = "we", name = "BBC"),
                title = "first news",
                url = "",
                urlToImage = ""
            )
        ) { }
    }
}