package com.example.news_app.newsapp.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news_app.newsapp.domain.model.Article
import com.example.news_app.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val newsUseCases: NewsUseCases) : ViewModel() {
    var sideEffect by mutableStateOf<String?>(null)
        private set

    private val _isBookmarked = mutableStateOf(false)
    var isBookMarked: State<Boolean> = _isBookmarked

    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.IsArticleBookmarked -> {
                viewModelScope.launch {
                    _isBookmarked.value=newsUseCases.getArticleFromUrl(articleUrl = event.articleUrl) != null
                }
            }
            is DetailsEvent.InsertDeleteArticleDetail -> {
                viewModelScope.launch {
                    val article = newsUseCases.getArticleFromUrl(articleUrl = event.article.url)
                    if (article == null) {
                        insertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }

                }
            }

            is DetailsEvent.RemoveSideEffect -> {
                sideEffect = null
            }
        }
    }

    private suspend fun insertArticle(article: Article) {
        newsUseCases.insertArticle(article = article)
        _isBookmarked.value=true
        sideEffect = "Article Inserted"
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCases.deleteArticle(article)
        _isBookmarked.value=false
        sideEffect = "Article Deleted"
    }

}