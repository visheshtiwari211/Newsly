package com.vishesh.newsly.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.detail.navigation.articleDetailsScreen
import com.example.detail.navigation.navigateToArticleDetail
import com.example.favorite.navigation.FavoriteScreenSection
import com.example.feed.navigation.FeedScreenSection
import com.example.feed.navigation.feedScreenRoute

@Composable
fun NewslyNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = feedScreenRoute,
        modifier = modifier,
        enterTransition = NavAnimations.enter,
        exitTransition = NavAnimations.exit,
        popEnterTransition = NavAnimations.popEnter,
        popExitTransition = NavAnimations.popExit
    ) {
        FeedScreenSection(
            onArticleClick = { url ->
                navController.navigateToArticleDetail(
                    url = url
                )
            },
            onBackClick = { navController.popBackStack() },
            detailScreenDestination = {
                articleDetailsScreen(onBackClick = { navController.popBackStack() })
            }
        )

        FavoriteScreenSection(onBackClick = { navController.popBackStack() })
    }
}