package com.example.news_app.newsapp.presentation.news_navigator.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.AndroidUiModes
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.news_app.R
import com.example.news_app.newsapp.presentation.onboarding.Dimens.extraSmallPadding
import com.example.news_app.newsapp.presentation.onboarding.Dimens.smallIconSize
import com.example.news_app.newsapp.ui.News_AppTheme

@Composable
fun NewsBottomNavigation(
    items: List<BottomNavigationItem>,
    selected: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selected,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = null,
                            modifier = Modifier.size(smallIconSize)
                        )
                        Spacer(modifier = Modifier.height(extraSmallPadding))
                        Text(text = item.text, style = MaterialTheme.typography.labelSmall)
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = colorResource(R.color.body),
                    unselectedIconColor = colorResource(R.color.body)
                )
            )
        }
    }
}

data class BottomNavigationItem(@DrawableRes val icon: Int, val text: String)

@Preview(showBackground = true,device = Devices.PIXEL_7,
    apiLevel = 33)
@Preview(uiMode = AndroidUiModes.UI_MODE_NIGHT_YES,device = Devices.PIXEL_7,
    apiLevel = 33)
@Composable
fun NewsBottomNavigationPreview() {
    News_AppTheme {
        NewsBottomNavigation(
            items = listOf(
                BottomNavigationItem(
                    icon = R.drawable.ic_home,
                    text = "Home"
                ),
                BottomNavigationItem(
                    icon = R.drawable.ic_search,
                    text = "Search"
                ),
                BottomNavigationItem(
                    icon = R.drawable.ic_bookmark,
                    text = "Bookmark"
                )
            ), selected = 2, onItemClick = {}
        )
    }
}