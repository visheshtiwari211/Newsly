package com.example.data.mapper

import com.example.database.local.ArticleEntity
import com.example.database.local.SourceEntity
import com.example.model.model.Article
import com.example.model.model.Source
import com.example.network.dto.ArticleDto
import com.example.network.dto.SourceDto

fun ArticleDto.toEntity(): ArticleEntity {
    return ArticleEntity(
        author = this.author,
        description = this.description,
        publishedAt = this.publishedAt,
        sourceEntity = this.source.toSourceEntity(),
        title = this.title,
        url = this.url,
        urlToImage = this.urlToImage,
        content = this.content
    )
}

fun SourceDto.toSourceEntity(): SourceEntity  = SourceEntity(name = this.name, sourceId = this.sourceId)

fun List<ArticleDto>.toListEntity(): List<ArticleEntity> {
    return this.map {
        it.toEntity()
    }
}

fun List<ArticleEntity>.toArticles(): List<Article> {
    return this.map {
        it.toArticle()
    }
}

fun ArticleEntity.toArticle(): Article {
    return Article(
        author = this.author ?: "",
        content = this.content ?: "",
        description = this.description ?: "",
        publishedAt = this.publishedAt ?: "",
        source = this.sourceEntity.toSource() ?: Source(sourceId = "", name = ""),
        title = this.title ?: "",
        url = this.url ?: "",
        urlToImage = this.urlToImage ?: ""
    )
}

fun SourceEntity.toSource(): Source = Source(sourceId = this.sourceId, name = this.name)