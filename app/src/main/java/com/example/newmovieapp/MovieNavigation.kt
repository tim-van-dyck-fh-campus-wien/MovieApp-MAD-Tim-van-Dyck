package com.example.newmovieapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newmovieapp.models.getMovies

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "homescreen"){
        composable("homescreen"){  HomeScreen(navController = navController) }
        composable("detailscreen/{movieId}", arguments = listOf(navArgument("movieId"){
            type = NavType.StringType//set type or argument
        })){ backStackEntry ->  DetailScreen(movieId = backStackEntry.arguments?.getString("movieId"), navController = navController)}
        composable("favoritesscreen"){ FavoritesScreen(navController = navController)}
    }
}
