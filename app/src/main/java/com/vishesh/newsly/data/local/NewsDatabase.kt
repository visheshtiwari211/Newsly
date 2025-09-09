package com.vishesh.newsly.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = true)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun articlesDao(): ArticleDao
}