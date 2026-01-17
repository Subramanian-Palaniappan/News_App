package com.example.news_app.newsapp.presentation.home

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.news_app.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import java.time.Instant
import java.time.Duration
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCases: NewsUseCases) : ViewModel() {

    val news = useCases.getNews(sources = listOf("bbc-news", "abc-news")).cachedIn(viewModelScope)

}