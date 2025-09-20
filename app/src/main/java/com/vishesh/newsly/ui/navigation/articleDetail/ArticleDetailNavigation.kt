package com.vishesh.newsly.ui.navigation.articleDetail

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.vishesh.newsly.ui.news.ui.articledetail.ArticleDetailScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

const val article_detail_screen_rotue = "article_detail_screen_route/{url}"

fun NavController.navigateToArticleDetail(
    modifier: Modifier = Modifier,
    url: String,
    navOptions: NavOptions? = null
) {
    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
    this.navigate("article_detail_screen_route/$encodedUrl", navOptions)
}

fun NavGraphBuilder.articleDetailsScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    composable(
        route = article_detail_screen_rotue,
        arguments = listOf(navArgument("url") { type = NavType.StringType })
    ) {
        ArticleDetailScreen(onBackClick = onBackClick)
    }
}

