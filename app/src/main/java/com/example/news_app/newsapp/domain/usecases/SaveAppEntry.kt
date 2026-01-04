package com.example.news_app.newsapp.domain.usecases

import com.example.news_app.newsapp.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke() {
        return localUserManager.saveAppEntry()
    }
}