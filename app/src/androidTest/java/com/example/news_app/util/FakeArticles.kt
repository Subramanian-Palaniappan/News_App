package com.example.news_app.util

import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.domain.model.Source

val fakeArticles = listOf(
    Article(
        author = "Emily Shapiro",
        content = "© 2025 ABC News",
        description = "Tatiana Schlossberg, the daughter of Caroline Kennedy, has died following a battle with acute myeloid leukemia.",
        publishedAt = "2025-12-30T19:34:28Z",
        source = Source(
            id = "abc-news",
            name = "ABC News"
        ),
        title = "Tatiana Schlossberg, granddaughter of JFK, has died",
        url = "https://abcnews.go.com/article1",
        urlToImage = "https://i.abcnewsfe.com/article11"
    ),

    Article(
        author = "The Associated Press",
        content = "SAN SALVADOR, El Salvador -- El Salvador President Nayib Bukele said Thursday that his administration is partnering with Elon Musk’s artificial intelligence company xAI to bring artificial intelligence into more than 5,000 public schools.",
        description = "El Salvador teams up with Elon Musk's xAI to bring AI to 5,000 public schools",
        publishedAt = "2025-12-11T18:29:29Z",
        source = Source(
            id = "abc-news",
            name = "ABC News"
        ),
        title = "El Salvador teams up with Elon Musk's xAI to bring AI to 5,000 public schools",
        url = "https://abcnews.go.com/article2",
        urlToImage = "https://i.abcnewsfe.com/article22"
    )
)