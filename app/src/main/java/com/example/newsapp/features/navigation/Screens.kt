package com.example.newsapp.features.navigation


enum class Screens{
    Home, Bookmarks, Search, NewsItem
}
sealed class NavigationItem(val route: String) {
    object HomeScreen: NavigationItem(Screens.Home.name)
    object SearchScreen: NavigationItem(Screens.Search.name)
    object BookmarksScreen: NavigationItem(Screens.Bookmarks.name)
    object DetailScreen: NavigationItem(Screens.NewsItem.name)
}