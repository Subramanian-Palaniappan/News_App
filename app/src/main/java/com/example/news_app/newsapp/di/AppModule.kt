package com.example.news_app.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.news_app.newsapp.NewsApplication
import com.example.news_app.newsapp.data.local.NewsDao
import com.example.news_app.newsapp.data.local.NewsRoomDataBase
import com.example.news_app.newsapp.data.local.SourceTypeConverter
import com.example.news_app.newsapp.data.remote.dto.NewsApi
import com.example.news_app.newsapp.data.repository.NewsRepositoryImpl
import com.example.news_app.newsapp.domain.manager.LocalUserManager
import com.example.news_app.newsapp.domain.repository.NewsRepository
import com.example.news_app.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.news_app.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.example.news_app.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.example.news_app.newsapp.domain.usecases.news.DeleteArticle
import com.example.news_app.newsapp.domain.usecases.news.GetArticleFromUrl
import com.example.news_app.newsapp.domain.usecases.news.GetArticles
import com.example.news_app.newsapp.domain.usecases.news.GetNews
import com.example.news_app.newsapp.domain.usecases.news.InsertArticle
import com.example.news_app.newsapp.domain.usecases.news.NewsUseCases
import com.example.news_app.newsapp.domain.usecases.news.SearchNews
import com.example.news_app.newsapp.util.Constants.BASE_URL
import com.example.news_app.newsapp.util.Constants.NEWS_ROOM_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
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

//    @Provides
//    @Singleton
//    fun provideOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .build()
//    }

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(okHtt)
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository =
        NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun provideGetNewsUseCases(newsRepository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            insertArticle = InsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            getArticles = GetArticles(newsRepository),
            getArticleFromUrl = GetArticleFromUrl(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun providesNewsDataBase(application: Application): NewsRoomDataBase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsRoomDataBase::class.java,
            name = NEWS_ROOM_DATABASE
        ).addTypeConverter(SourceTypeConverter()).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesNewsDao(newsRoomDataBase: NewsRoomDataBase): NewsDao {
        return newsRoomDataBase.newsDao
    }

}