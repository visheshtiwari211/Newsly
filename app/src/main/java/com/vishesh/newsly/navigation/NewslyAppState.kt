package com.vishesh.newsly.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

class NewslyAppState(
    private val navHostController: NavHostController,
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


}