package com.example.newmovieapp

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    val favoritesViewModel:FavoritesViewModel = viewModel()
    NavHost(navController = navController, startDestination = "homescreen"){
        composable("homescreen"){  HomeScreen(navController = navController,favoritesVM=favoritesViewModel) }
        composable("detailscreen/{movieId}", arguments = listOf(navArgument("movieId"){
            type = NavType.StringType//set type or argument
        })){ backStackEntry ->  DetailScreen(movieId = backStackEntry.arguments?.getString("movieId"), navController = navController, favoritesVM=favoritesViewModel)}
        composable("favoritesscreen"){ FavoritesScreen(navController = navController,favoritesVM=favoritesViewModel)}
    }
}
