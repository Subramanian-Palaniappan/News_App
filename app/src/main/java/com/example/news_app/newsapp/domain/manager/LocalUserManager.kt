package com.example.news_app.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}