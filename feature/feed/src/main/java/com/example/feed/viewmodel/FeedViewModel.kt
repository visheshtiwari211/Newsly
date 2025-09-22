package com.example.feed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.data.repository.NewsRepository
import com.example.model.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {
    val pagedArticle: Flow<PagingData<Article>> =
        repository.getPagedArticles(country = "us", apiKey = "35e96e729a6848a48c1c31aaf412b671")
            .cachedIn(viewModelScope)

}