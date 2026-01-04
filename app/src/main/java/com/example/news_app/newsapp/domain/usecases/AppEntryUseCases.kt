package com.example.news_app.newsapp.domain.usecases

import javax.inject.Inject

data class AppEntryUseCases @Inject constructor(val readAppEntry: ReadAppEntry, val saveAppEntry: SaveAppEntry)
