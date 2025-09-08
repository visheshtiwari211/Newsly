package com.vishesh.newsly.data.repository

import com.vishesh.newsly.data.api.NewsApi
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi) {
    suspend fun getTopHeadline(country: String, category: String, apiKey: String) =
        newsApi.getTopHeadlines(country = country, apiKey = apiKey)
}