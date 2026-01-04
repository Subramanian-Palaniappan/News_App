package com.example.news_app.newsapp.di

import com.example.news_app.newsapp.NewsApplication
import com.example.news_app.newsapp.data.manager.LocalUserManagerImpl
import com.example.news_app.newsapp.domain.manager.LocalUserManager
import com.example.news_app.newsapp.domain.usecases.AppEntryUseCases
import com.example.news_app.newsapp.domain.usecases.ReadAppEntry
import com.example.news_app.newsapp.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun providesLocalUserManager(application: NewsApplication): LocalUserManager =
//        LocalUserManagerImpl(application)
//
    @Provides
    @Singleton
    fun providesAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager = localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

}