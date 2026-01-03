package com.example.news_app.newsapp.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.news_app.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val pages=listOf(
    Page(
        title = "News That Matters to You",
        description = "Choose your interests and receive personalized news tailored to what you care about.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Read News on the Go",
        description = "Access news anytime, anywhere with a smooth and distraction-free reading experience.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Stay Updated Anytime",
        description = "Get the latest breaking news and top stories from trusted sources, all in one place.",
        image = R.drawable.onboarding3
    )
)
