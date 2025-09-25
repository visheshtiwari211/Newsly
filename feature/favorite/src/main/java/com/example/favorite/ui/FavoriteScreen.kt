package com.example.favorite.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.favorite.viewmodel.FavoriteViewModel
import com.example.model.model.Article
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(onBackClick: () -> Unit, viewModel: FavoriteViewModel = hiltViewModel()) {
    val favoriteArticles by viewModel.favoriteArticles.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    val errorMessage = favoriteArticles.error
    LaunchedEffect(errorMessage) {
        if (!errorMessage.isNullOrBlank()) {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(
                    message = errorMessage,
                    actionLabel = "Dismiss"
                )
            }
        }
    }

    when {
        favoriteArticles.isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        favoriteArticles.articles.isEmpty() -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No favorite articles yet")
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(count = favoriteArticles.articles.size) { index ->
                    val article = favoriteArticles.articles[index]
                    FavoriteArticleItem(article = article)
                }
            }
        }
    }
}

@Composable
fun FavoriteArticleItem(article: Article) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = article.title ?: "",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black
            )
            article.url?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}
