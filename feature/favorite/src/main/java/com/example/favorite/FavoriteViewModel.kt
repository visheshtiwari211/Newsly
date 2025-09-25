package com.example.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.NewsUiState
import com.example.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(val repository: NewsRepository): ViewModel() {
    private val _favoriteArticles: MutableStateFlow<NewsUiState> = MutableStateFlow(NewsUiState())
    val favoriteArticles = _favoriteArticles.asStateFlow()

    init {
        getFavoriteArticles()
    }

    fun getFavoriteArticles() {
        viewModelScope.launch {
            try {
                _favoriteArticles.value = _favoriteArticles.value.copy(isLoading = true)
                _favoriteArticles.value = NewsUiState(articles = repository.getFavoriteArticles())
            } catch (e: Exception) {
                _favoriteArticles.value = _favoriteArticles.value.copy(error = e.message)
            }

        }
    }
}