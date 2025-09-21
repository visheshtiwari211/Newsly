package com.example.data.repository

import android.util.Log
import com.example.data.mapper.toArticle
import com.example.data.mapper.toArticles
import com.example.data.mapper.toListEntity
import com.example.database.local.ArticleDao
import com.example.model.model.Article
import com.example.network.api.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi, private val articleDao: ArticleDao) {
    fun getAllArticles(): Flow<List<Article>> = articleDao.getAllArticles().map { it.toArticles() }

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

    suspend fun getArticleFromUrl(url: String): Article = articleDao.getArticleFromUrl(url = url).toArticle()
}