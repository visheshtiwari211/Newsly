package com.vishesh.newsly.ui.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishesh.newsly.data.mapper.toArticles
import com.vishesh.newsly.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {
    private val _uiState: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchTopHeadline()
    }

    fun fetchTopHeadline() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                repository.getTopHeadline(country = "us", apiKey = "")
                repository.getAllArticles().collect { articleEntities ->
                    val articles = articleEntities.toArticles()
                    _uiState.value = NewsUiState(
                        isLoading = false,
                        articles = articles,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.value = NewsUiState(isLoading = false, error = e.message)
            }
        }
    }

}