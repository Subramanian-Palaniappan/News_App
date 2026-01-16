package com.example.news_app.newsapp.presentation.onboarding.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.news_app.R
import com.example.news_app.newsapp.presentation.onboarding.Dimens.padding_24
import com.example.news_app.newsapp.presentation.onboarding.Dimens.padding_30
import com.example.news_app.newsapp.presentation.onboarding.Page
import com.example.news_app.newsapp.ui.News_AppTheme

@Composable
fun OnBoardingPage(modifier: Modifier = Modifier, page: Page) {
    //This column refers to image, title and description ui in onboarding page
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(padding_24))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = padding_30),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.display_small)
        )
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = padding_30),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )

    }

}
private val previewPage = Page(
    title = "Stay Updated",
    description = "Get the latest news instantly",
    image = R.drawable.ic_launcher_foreground
)

@Preview(showBackground = true, device = Devices.NEXUS_7, apiLevel = 33)
@Preview(uiMode = UI_MODE_NIGHT_YES, device = Devices.NEXUS_7, apiLevel = 33)
@Composable
fun OnBoardingPagePreview() {
    News_AppTheme {
        OnBoardingPage(
            page = previewPage
        )
    }
}