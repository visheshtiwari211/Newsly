package com.example.database.di

import android.content.Context
import androidx.room.Room
import com.example.database.db.NewsDatabase
import com.example.database.local.ArticleDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return Room.databaseBuilder(context, NewsDatabase::class.java, "news_db")
            .fallbackToDestructiveMigration(true)
            .build()
    }

    @Provides
    fun provideArticleDao(db: NewsDatabase): ArticleDao {
        return db.articlesDao()
    }

}