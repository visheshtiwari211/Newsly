package com.example.data.di

import com.example.data.repository.NewsRepository
import com.example.database.db.NewsDatabase
import com.example.database.local.ArticleDao
import com.example.network.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun providesNewsRepository(newsApi: NewsApi, articleDao: ArticleDao, newsDatabase: NewsDatabase): NewsRepository {
        return NewsRepository(newsApi = newsApi, articleDao = articleDao, newsDatabase = newsDatabase)
    }
}