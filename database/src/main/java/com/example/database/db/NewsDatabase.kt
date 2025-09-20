package com.example.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.local.ArticleDao
import com.example.database.local.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 2, exportSchema = true)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun articlesDao(): ArticleDao
}