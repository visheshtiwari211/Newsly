package com.vishesh.newsly.ui.news.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishesh.newsly.data.mapper.toArticles
import com.vishesh.newsly.data.repository.NewsRepository
import com.vishesh.newsly.ui.news.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {
    private val _uiState: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchTopHeadline()
    }

    fun fetchTopHeadline() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                repository.getTopHeadline(country = "us", apiKey = "35e96e729a6848a48c1c31aaf412b671")
                repository.getAllArticles().collect { articleEntities ->
                    Log.d("NewsViewModel", "articleEntities: $articleEntities")
                    articleEntities?.let { articleEntities ->
                        val articles = articleEntities.toArticles()
                        _uiState.value = NewsUiState(
                            isLoading = false,
                            articles = articles,
                            error = null
                        )
                    } ?: Log.e("NewsViewModel", "articleEntities is null")
                }
            } catch (e: Exception) {
                Log.e("NewsViewModel", "Exception: ${e.message}")
                _uiState.value = NewsUiState(isLoading = false, error = e.message)
            }
        }
    }

}