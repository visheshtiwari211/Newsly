package com.vishesh.newsly.data.repository

import android.util.Log
import com.vishesh.newsly.data.api.NewsApi
import com.vishesh.newsly.data.local.ArticleDao
import com.vishesh.newsly.data.local.ArticleEntity
import com.vishesh.newsly.data.mapper.toArticles
import com.vishesh.newsly.data.mapper.toListEntity
import com.vishesh.newsly.domain.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi, private val articleDao: ArticleDao) {
    fun getAllArticles(): Flow<List<ArticleEntity>> = articleDao.getAllArticles()

    suspend fun getTopHeadline(country: String, apiKey: String) {
        Log.d("NewsRepository", "inside getTopHeadlines")
        val response = newsApi.getTopHeadlines(country = country, apiKey = apiKey)
        if (response.isSuccessful) {
            Log.d("NewsRepository", "inside getTopHeadlines response: ${response.body()}")
            response.body()?.articles?.let { articlesDto ->
                val articlesEntityList = articlesDto.toListEntity()
                articleDao.insertArticles(articlesEntityList)
            } ?: Log.e("NewsRepository", "getTopHeadlines ResponseBody articles is null: $response")
        } else {
            Log.e("NewsRepository", "getTopHeadlines Response is null")
        }
    }

    suspend fun getArticleFromUrl(url: String): Article = articleDao.getArticleFromUrl(url = url).toArticles()
}