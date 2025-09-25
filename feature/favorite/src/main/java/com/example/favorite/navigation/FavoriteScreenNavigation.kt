package com.example.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.favorite.ui.FavoriteScreen

const val favScreenRoute = "fav_screen_route"

fun NavController.navigateToFavScreen(navOptions: NavOptions? = null) {
    return this.navigate(route = favScreenRoute, navOptions = navOptions)
}

fun NavGraphBuilder.FavoriteScreenSection(onBackClick: () -> Unit) {
    composable(route = favScreenRoute) {
        FavoriteScreen(onBackClick = onBackClick)
    }
}