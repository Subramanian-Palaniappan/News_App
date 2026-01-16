package com.example.news_app.newsapp.domain.usecases.news

data class NewsUseCases (
    val getNews:GetNews,
    val searchNews: SearchNews,
    val insertArticle: InsertArticle,
    val deleteArticle: DeleteArticle,
    val getArticles: GetArticles,
    val getArticleFromUrl: GetArticleFromUrl
)