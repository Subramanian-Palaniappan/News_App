package com.example.news_app.newsapp.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news_app.R
import com.example.news_app.newsapp.presentation.onboarding.Dimens.articleCardSize
import com.example.news_app.newsapp.presentation.onboarding.Dimens.extraSmallPadding
import com.example.news_app.newsapp.presentation.onboarding.Dimens.padding_30
import com.example.news_app.newsapp.ui.News_AppTheme

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition()
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        )
    ).value
    background(color = colorResource(R.color.shimmer).copy(alpha = alpha))
}

@Composable
fun ArticleCardShimmerEffect(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .size(articleCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect()
        )

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(extraSmallPadding)
                .height(
                    articleCardSize
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(padding_30)
                    .padding(horizontal = padding_30)
                    .shimmerEffect()
            )
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {

                Box(
                    modifier = Modifier
                        .weight(0.45f)
                        .height(15.dp)
                        .padding(paddingValues = PaddingValues(start = padding_30))
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .weight(0.1f)
                        .height(15.dp)
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .weight(0.45f)
                        .height(15.dp)
                        .padding(paddingValues = PaddingValues(end = padding_30))
                        .shimmerEffect()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardShimmerPreview() {
    News_AppTheme { ArticleCardShimmerEffect() }
}