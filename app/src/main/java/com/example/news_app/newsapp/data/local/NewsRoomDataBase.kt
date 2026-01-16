package com.example.news_app.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.news_app.newsapp.domain.model.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(SourceTypeConverter::class)
abstract class NewsRoomDataBase : RoomDatabase() {
    abstract val newsDao: NewsDao
}