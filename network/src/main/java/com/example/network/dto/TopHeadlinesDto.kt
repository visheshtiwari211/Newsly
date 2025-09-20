package com.example.network.dto

data class TopHeadlinesDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)