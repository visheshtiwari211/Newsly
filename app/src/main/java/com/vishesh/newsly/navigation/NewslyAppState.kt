package com.vishesh.newsly.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberNewslyAppState(
    navHostController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): NewslyAppState {
    return remember(navHostController, coroutineScope) {
        NewslyAppState(navHostController, coroutineScope)
    }
}

class NewslyAppState(
    val navHostController: NavHostController,
    coroutineScope: CoroutineScope
) {
    private val previousDestination: MutableState<NavDestination?> = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navHostController.currentBackStackEntryFlow.collectAsState(initial = null)
            return currentEntry.value?.destination.also {
                if(it != null) {
                    previousDestination.value = it
                }
            } ?: previousDestination.value
        }

    val currentTopLevelDestination: TopLevelDestination?
        @Composable
        get() {
            return TopLevelDestination.entries.firstOrNull { topLevelDestination ->
                currentDestination?.route == topLevelDestination.route
            }
        }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOption = navOptions {
            popUpTo(navHostController.graph.startDestinationId) {
                saveState = true
            }
            restoreState = true
            launchSingleTop = true
        }
        when(topLevelDestination) {
            TopLevelDestination.Feed -> navHostController.navigate(TopLevelDestination.Feed.route, topLevelNavOption)
            TopLevelDestination.Favorites -> navHostController.navigate(TopLevelDestination.Favorites.route, topLevelNavOption)
        }
    }
}