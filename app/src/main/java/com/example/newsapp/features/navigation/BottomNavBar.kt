package com.example.newsapp.features.navigation

import android.app.Application
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.core.SharedViewModel
import com.example.newsapp.features.pages.bookmarks.BookmarksScreenUI
import com.example.newsapp.features.pages.bookmarks.BookmarksViewmodel
import com.example.newsapp.features.pages.detail.DetailScreenUI
import com.example.newsapp.features.pages.detail.DetailViewModel
import com.example.newsapp.features.pages.home.HomeScreenUI
import com.example.newsapp.features.pages.home.HomeViewModel
import com.example.newsapp.features.pages.search.SearchScreenUI
import com.example.newsapp.features.pages.search.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavBar(innerpadding:PaddingValues){
    val navController = rememberNavController()
    val navBackstackEntry by navController.currentBackStackEntryAsState()
    val currRoute=navBackstackEntry?.destination?.route
    val sharedViewModel: SharedViewModel=viewModel()
    val context=LocalContext.current

    Scaffold(
        bottomBar = {
            NavigationBar {
                BottomNavItem().bottomNavigationItems().forEach { item->
                    NavigationBarItem(
                        selected = currRoute==item.route,
                        onClick = {
                            if (currRoute!=item.route){
                                navController.navigate(item.route){
                                    popUpTo(navController.graph.findStartDestination().id){
                                        saveState=true
                                    }
                                    launchSingleTop=true
                                    restoreState=true
                                }
                            }
                        },
                        icon = {
                            Icon(item.icon, contentDescription = item.label
                            )
                        },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) {paddingValues->
        NavHost(navController, startDestination = NavigationItem.HomeScreen.route){
            composable(NavigationItem.HomeScreen.route) {
                val viewmodel: HomeViewModel= viewModel()
                HomeScreenUI(
                    paddingValues, viewmodel,
                    onSearchbarClick ={
                        navController.navigate(NavigationItem.SearchScreen.route) {
                            launchSingleTop = true
                        }
                    },
                    goToDetailScreen = { article->
                        sharedViewModel.selectArticle(article)
                        navController.navigate(NavigationItem.DetailScreen.route)
                    }
                )
            }
            composable(NavigationItem.SearchScreen.route) {
                val viewModel: SearchViewModel=viewModel()
                SearchScreenUI(paddingValues,viewModel)
            }
            composable(NavigationItem.DetailScreen.route) {
                val viewModel: DetailViewModel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(
                            modelClass: Class<T>,
                            extras: CreationExtras
                        ): T {
                            return DetailViewModel(context.applicationContext as Application) as T
                        }
                    }
                )

                val article = sharedViewModel.article.value
                if (article!=null){
                    DetailScreenUI(
                        article = article,
                        onBackClick = {navController.popBackStack()},
                        viewmodel = viewModel
                    )
                }

            }
            composable(NavigationItem.BookmarksScreen.route) {
                val viewModel: BookmarksViewmodel = viewModel(
                    factory = object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(
                            modelClass: Class<T>,
                            extras: CreationExtras
                        ): T {
                            return BookmarksViewmodel(context.applicationContext as Application) as T
                        }
                    }
                )
                BookmarksScreenUI(
                    innerPadding = innerpadding,
                    goToDetailScreen = {
                        sharedViewModel.selectArticle(it)
                        navController.navigate(NavigationItem.DetailScreen.route)
                    },
                    viewmodel = viewModel
                )
            }
        }
    }
}