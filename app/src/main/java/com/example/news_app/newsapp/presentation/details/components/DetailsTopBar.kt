package com.example.news_app.newsapp.presentation.details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.AndroidUiModes.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview
import com.example.news_app.R
import com.example.news_app.newsapp.ui.News_AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    isBookmarked: Boolean,
    onBackClick: () -> Unit
) {

    TopAppBar(
        title = { },
        modifier = Modifier
            .testTag("details_top_bar")
            .fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(R.color.body)
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick, modifier = Modifier.testTag("back_button")) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconToggleButton(onCheckedChange = { onBookmarkClick() }, checked = isBookmarked, modifier = Modifier.testTag("bookmark_button")) {
                Icon(
                    painter = if (isBookmarked) painterResource(R.drawable.ic_bookmark_filled) else painterResource(
                        R.drawable.ic_bookmark
                    ),
                    contentDescription = null,
//                    tint = if(isBookmarked) Color.Red else Color.Transparent
                )
            }
            IconButton(onClick = onShareClick, modifier = Modifier.testTag("share_button")) {
                Icon(
                    imageVector = Icons.Filled.Share,
                    contentDescription = null
                )
            }
            IconButton(onClick = onBrowsingClick, modifier = Modifier.testTag("browse_button")) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = null
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DetailsTopBarPreview() {
    News_AppTheme {
        DetailsTopBar(
            onBrowsingClick = {},
            onShareClick = {},
            onBackClick = {},
            onBookmarkClick = {},
            isBookmarked = true
        )
    }

}
