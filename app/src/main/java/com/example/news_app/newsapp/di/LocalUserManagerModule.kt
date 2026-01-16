package com.example.news_app.newsapp.di

import com.example.news_app.newsapp.data.manager.LocalUserManagerImpl
import com.example.news_app.newsapp.domain.manager.LocalUserManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalUserManagerModule {

    @Binds
    abstract fun bindLocalUserManager(
        impl: LocalUserManagerImpl
    ): LocalUserManager
}