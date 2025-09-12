package com.vishesh.newsly.data

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vishesh.newsly.data.local.ArticleDao
import com.vishesh.newsly.data.local.ArticleEntity
import com.vishesh.newsly.data.local.NewsDatabase
import com.vishesh.newsly.data.model.Source
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {
    private lateinit var db: NewsDatabase
    private lateinit var articleDao: ArticleDao

    @Before
    fun setup() {
        try {
            Log.d("ArticleDaoTest", "setup starting...")
            db = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(),
                NewsDatabase::class.java
            ).allowMainThreadQueries().build()
            articleDao = db.articlesDao()
            Log.d("ArticleDaoTest", "setup finished")
        } catch (e: Exception) {
            Log.e("ArticleDaoTest", "setup exception: ${e.message}")
        }
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insetAndQueryTitle() {
        runBlocking {
            val article = ArticleEntity(
                id = 0,
                author = "John Doe",
                title = "Test News",
                description = "This is a test description",
                url = "https://example.com/news/1",
                urlToImage = "",
                publishedAt = "2024-09-09T10:00:00Z",
                source = Source("0", "")
            )
            articleDao.insertArticles(listOf(article))
            val articleTest = articleDao.getAllArticles()
            // Query
            val articles = articleDao.getAllArticles()

            assertEquals(1, articles.size)
            assertEquals("Test News", articles[0].title)
            Log.d("ArticleDaoTest", "articleTest: ${articleTest.get(0).title}")
        }
    }
}