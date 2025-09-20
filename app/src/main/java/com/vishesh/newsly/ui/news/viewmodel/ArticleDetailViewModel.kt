package com.vishesh.newsly.ui.news.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishesh.newsly.data.repository.NewsRepository
import com.vishesh.newsly.ui.news.NewsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailViewModel @Inject constructor(private val repository: NewsRepository, savedStateHandle: SavedStateHandle): ViewModel() {
    private val _uiState: MutableLiveData<NewsUiState> = MutableLiveData()
    val uiState: LiveData<NewsUiState> = _uiState

    init {
        val url = savedStateHandle.get<String>("url") ?: ""
        fetchArticle(url = url)
    }

    private fun fetchArticle(url: String) {
        viewModelScope.launch {
            Log.d("ArticleDetailViewModel", "fetchArticle: $url")
            _uiState.value = NewsUiState(isLoading = true)
            try {
                val article = repository.getArticleFromUrl(url)
                _uiState.value = NewsUiState(isLoading = false, articles = listOf(article), error = null)
            } catch (e: Exception) {
                Log.d("ArticleDetailViewModel", "fetchArticle error: ${e.message}")
                _uiState.value = NewsUiState(error = e.message)
            }
        }
    }
}