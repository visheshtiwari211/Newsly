package com.example.feed.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.feed.ui.components.FeedItemCard
import com.example.feed.viewmodel.FeedViewModel
import com.example.model.model.Article
import com.example.model.model.Source

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    onArticleClick: (String) -> Unit,
    viewModel: FeedViewModel = hiltViewModel()
) {
    val articles = viewModel.pagedArticle.collectAsLazyPagingItems()
    Log.d("FeedScreen", "uiState: $articles")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = "", // TODO: wire with search state in ViewModel
                onValueChange = { /* viewModel.onSearchQueryChanged(it) */ },
                placeholder = { Text("Search articles...", style = MaterialTheme.typography.bodyMedium)},
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(24.dp)
            )

            Button(
                onClick = { /* TODO: Menu action */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.height(48.dp)
            ) {
                Text("Menu", color = Color.White, style = MaterialTheme.typography.bodyMedium)
            }
        }

        LazyColumn (
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(count = articles.itemCount) { index ->
                val article = articles[index] ?: Article(
                    author = "",
                    content = "",
                    description = "",
                    publishedAt = "",
                    source = Source("", ""),
                    title = "",
                    url = "",
                    urlToImage = ""
                )
                FeedItemCard(article, onArticleClick = onArticleClick)
            }
        }
    }
}