package com.example.news_app.newsapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.news_app.newsapp.domain.usecases.news.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(val searchNewsUseCase: NewsUseCases) : ViewModel() {

    private val _state = mutableStateOf(SearchState())
    var state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.updateSearchQuery -> {
                _state.value = state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.searchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = searchNewsUseCase.searchNews(
            searchQuery = state.value.searchQuery,
            sources = listOf("bbc-news", "abc-news")
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles)
    }
}