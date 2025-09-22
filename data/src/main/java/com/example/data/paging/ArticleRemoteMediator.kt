package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.mapper.toListEntity
import com.example.database.db.NewsDatabase
import com.example.database.local.ArticleDao
import com.example.database.local.ArticleEntity
import com.example.network.api.NewsApi

@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator(
    private val newsApi: NewsApi,
    private val articleDao: ArticleDao,
    private val newsDatabase: NewsDatabase,
    private val country: String,
    private val key: String
) : RemoteMediator<Int, ArticleEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    lastItem.id
                }
            }
            val response = newsApi.getTopHeadlines(country = country, apiKey = key)

            newsDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDao.deleteAll()
                }
            }
            val articles = response.body()?.articles?.toListEntity() ?: emptyList()
            articleDao.insertArticles(articles)
            return MediatorResult.Success(endOfPaginationReached = articles.isEmpty())
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}