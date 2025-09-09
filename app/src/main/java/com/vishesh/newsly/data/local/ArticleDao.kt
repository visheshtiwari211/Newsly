package com.vishesh.newsly.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vishesh.newsly.data.model.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createArticles(articles: List<Article>)

    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): List<Article>

    @Query("DELETE FROM articles")
    suspend fun deleteAll()
}