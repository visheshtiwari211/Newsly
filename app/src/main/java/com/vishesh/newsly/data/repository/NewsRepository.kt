package com.vishesh.newsly.data.repository

import android.util.Log
import com.vishesh.newsly.data.api.NewsApi
import com.vishesh.newsly.data.local.ArticleDao
import com.vishesh.newsly.data.local.ArticleEntity
import com.vishesh.newsly.data.mapper.toListEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi, private val articleDao: ArticleDao) {
    fun getAllArticles(): Flow<List<ArticleEntity>> = articleDao.getAllArticles()

    suspend fun getTopHeadline(country: String, apiKey: String) {
        val response = newsApi.getTopHeadlines(country = country, apiKey = apiKey)
        if (response.isSuccessful) {
            response.body()?.articleDtos?.let { articlesDto ->
                val articlesEntityList = articlesDto.toListEntity()
                articleDao.insertArticles(articlesEntityList)
            } ?: Log.e("NewsRepository", "getTopHeadlines ResponseBody articles is null: $response")
        } else {
            Log.e("NewsRepository", "getTopHeadlines Response is null")
        }
    }
}