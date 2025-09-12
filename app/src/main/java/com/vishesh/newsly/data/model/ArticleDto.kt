package com.vishesh.newsly.data.model

data class ArticleDto(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceDto: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String
)