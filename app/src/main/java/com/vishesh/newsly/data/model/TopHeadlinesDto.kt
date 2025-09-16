package com.vishesh.newsly.data.model

data class TopHeadlinesDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)