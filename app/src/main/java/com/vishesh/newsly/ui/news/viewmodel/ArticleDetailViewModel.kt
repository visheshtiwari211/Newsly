package com.vishesh.newsly.ui.news.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishesh.newsly.data.repository.NewsRepository
import com.vishesh.newsly.ui.news.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(private val repository: NewsRepository, savedStateHandle: SavedStateHandle): ViewModel() {
    private val _uiState: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val url = savedStateHandle.get<String>("url") ?: ""
        fetchArticle(url = url)
    }

    private fun fetchArticle(url: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val article = repository.getArticleFromUrl(url)
            _uiState.value = NewsUiState(isLoading = false, articles = listOf(article), error = null)
        }
    }
}