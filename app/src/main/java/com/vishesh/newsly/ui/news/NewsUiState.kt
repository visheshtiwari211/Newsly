package com.vishesh.newsly.ui.news

import com.vishesh.newsly.domain.Article

data class NewsUiState(
    val isLoading: Boolean = false,
    val articles: List<Article> = emptyList<Article>(),
    val error: String? = null
)