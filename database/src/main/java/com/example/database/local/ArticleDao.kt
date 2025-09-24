package com.example.database.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM articles")
    fun getAllArticles(): PagingSource<Int, ArticleEntity>

    @Query("DELETE FROM articles")
    suspend fun deleteAll()

    @Query("SELECT * FROM articles WHERE url = :url LIMIT 1")
    suspend fun getArticleFromUrl(url: String): ArticleEntity

    @Query("UPDATE articles SET isFavorite = :isFavorite WHERE url = :url")
    suspend fun setFavoriteArticle(url: String, isFavorite: Boolean)

    @Query("SELECT * FROM articles WHERE isFavorite = 1")
    suspend fun getFavoriteArticles(): List<ArticleEntity>
}