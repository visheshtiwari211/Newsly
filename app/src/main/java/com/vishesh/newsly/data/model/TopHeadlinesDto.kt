package com.vishesh.newsly.data.model

data class TopHeadlinesDto(
    val articleDtos: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)