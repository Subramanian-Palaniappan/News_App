package com.example.news_app.newsapp.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.news_app.newsapp.presentation.common.NewsButton
import com.example.news_app.newsapp.presentation.common.NewsTextButton
import com.example.news_app.newsapp.presentation.onboarding.Dimens.padding_30
import com.example.news_app.newsapp.presentation.onboarding.Dimens.pageIndicatorWidth
import com.example.news_app.newsapp.presentation.onboarding.components.OnBoardingPage
import com.example.news_app.newsapp.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen() {
    //Column contains whole onboarding content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        //used remeber pager state to hold and preserve pager's' scroll
        val pageState = rememberPagerState(
            initialPage = 0,
            pageCount = { pages.size }
        )
//used remember and derivedstateof for page indicator button state to avoid unnecessary recomposition during pager scroll
        val buttonState = remember {
            derivedStateOf {
                when (pageState.currentPage) {
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("", "")
                }
            }
        }

        HorizontalPager(
            state = pageState
        ) { index ->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        //Inside this row both pager indicator and back & next button are handled
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding_30)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            PageIndicator(
                modifier = Modifier.width(pageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pageState.currentPage
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                val scope = rememberCoroutineScope()

                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(  //newstextbutton for only back
                        text = buttonState.value[0],

                        onClick = {
                            scope.launch {
                                pageState.animateScrollToPage(pageState.currentPage - 1)
                            }
                        }
                    )
                }

                NewsButton(  // newsbutton for both next and getstarted it has background color and shape
                    text = buttonState.value[1],
                    onClick = {
                        scope.launch {
                            if (pageState.currentPage == pages.lastIndex) {
                                // TODO: Navigate to main screen
                            } else {
                                pageState.animateScrollToPage(pageState.currentPage + 1)
                            }
                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}
