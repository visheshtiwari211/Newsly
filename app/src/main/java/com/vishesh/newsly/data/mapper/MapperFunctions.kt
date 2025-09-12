package com.vishesh.newsly.data.mapper

import com.vishesh.newsly.data.local.ArticleEntity
import com.vishesh.newsly.data.model.ArticleDto
import com.vishesh.newsly.domain.Article

fun ArticleDto.toEntity(): ArticleEntity {
    return ArticleEntity(
        author = this.author,
        description = this.description,
        publishedAt = this.publishedAt,
        sourceDto = this.sourceDto,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage,
        content = this.content
    )
}

fun List<ArticleDto>.toListEntity(): List<ArticleEntity> {
    return this.map {
        it.toEntity()
    }
}

fun ArticleEntity.toArticles(): Article {
    return Article(
        author = this.author,
        content = this.content,
        description = this.description,
        publishedAt = this.publishedAt,
        source = this.sourceDto,
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage
    )
}