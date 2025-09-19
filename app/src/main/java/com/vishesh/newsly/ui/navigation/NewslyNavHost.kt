package com.vishesh.newsly.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.vishesh.newsly.ui.navigation.articleDetail.navigateToArticleDetail
import com.vishesh.newsly.ui.navigation.feed.FeedScreenSection
import com.vishesh.newsly.ui.navigation.feed.feedScreenRoute

@Composable
fun NewslyNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = feedScreenRoute,
        modifier = modifier
    ) {
        FeedScreenSection(
            onArticleClick = { url ->
                navController.navigateToArticleDetail(
                    url = url
                )
            },
            onBackClick = { navController.popBackStack() })
    }
}