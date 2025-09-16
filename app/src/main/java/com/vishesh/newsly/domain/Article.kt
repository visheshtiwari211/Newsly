package com.vishesh.newsly.domain

import com.vishesh.newsly.data.model.SourceDto

data class Article(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceDto?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
)
