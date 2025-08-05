package com.example.newsapp.features.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {
    fun bottomNavigationItems() : List<BottomNavItem> {
        return listOf(
            BottomNavItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = Screens.Home.name
            ),
            BottomNavItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = Screens.Search.name
            ),
            BottomNavItem(
                label = "Bookmarks",
                icon = Icons.Filled.FavoriteBorder,
                route = Screens.Bookmarks.name
            ),
        )
    }
}