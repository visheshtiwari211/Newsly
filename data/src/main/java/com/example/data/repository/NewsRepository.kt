package com.example.data.repository

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.data.mapper.toArticle
import com.example.data.mapper.toListEntity
import com.example.data.paging.ArticleRemoteMediator
import com.example.database.db.NewsDatabase
import com.example.database.local.ArticleDao
import com.example.model.model.Article
import com.example.network.api.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsApi: NewsApi,
    private val articleDao: ArticleDao,
    private val newsDatabase: NewsDatabase
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getPagedArticles(country: String, apiKey: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = ArticleRemoteMediator(
                newsApi = newsApi,
                newsDatabase = newsDatabase,
                articleDao = articleDao,
                country = country,
                key = apiKey
            ),
            pagingSourceFactory = { articleDao.getAllArticles() },
            initialKey = null,
        ).flow.map { pagingDataFlow ->
            pagingDataFlow.map { it.toArticle() }
        }
    }

    suspend fun getTopHeadline(country: String, apiKey: String) {
        Log.d("NewsRepository", "inside getTopHeadlines")
        val response = newsApi.getTopHeadlines(country = country, apiKey = apiKey)
        if (response.isSuccessful) {
            Log.d("NewsRepository", "inside getTopHeadlines response: ${response.body()}")
            response.body()?.articles?.let { articlesDto ->
                val favoriteArticlesEntityList = articleDao.getFavoriteArticles()
                val articlesEntityList = articlesDto.toListEntity(favoriteArticles = favoriteArticlesEntityList)
                articleDao.insertArticles(articlesEntityList)
            } ?: Log.e("NewsRepository", "getTopHeadlines ResponseBody articles is null: $response")
        } else {
            Log.e("NewsRepository", "getTopHeadlines Response is null")
        }
    }

    suspend fun getArticleFromUrl(url: String): Article =
        articleDao.getArticleFromUrl(url = url).toArticle()

    suspend fun setFavoriteArticles(url: String, isFavorite: Boolean) {
        articleDao.setFavoriteArticle(url = url, isFavorite = isFavorite)
    }
}