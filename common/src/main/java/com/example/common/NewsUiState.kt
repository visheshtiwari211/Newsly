package com.example.common

import com.example.model.model.Article

data class NewsUiState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList<Article>(),
    val error: String? = null
)