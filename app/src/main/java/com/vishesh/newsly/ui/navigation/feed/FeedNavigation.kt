package com.vishesh.newsly.ui.navigation.feed

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.vishesh.newsly.ui.news.ui.FeedScreen

const val feedScreenRoute = "feed_screen_route"

fun NavController.navigateToFeedScreen(navOptions: NavOptions) = this.navigate(feedScreenRoute, navOptions)

fun NavGraphBuilder.FeedScreenSection(modifier: Modifier = Modifier) {
    composable(route = feedScreenRoute) {
        FeedScreen()
    }
}