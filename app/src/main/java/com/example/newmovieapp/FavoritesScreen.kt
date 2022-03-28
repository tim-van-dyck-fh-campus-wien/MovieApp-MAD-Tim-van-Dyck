package com.example.newmovieapp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.newmovieapp.models.Movie
import com.example.newmovieapp.models.getMovies

@Composable
fun FavoritesScreen(navController:NavController){
    var movies= listOf<Movie>(getMovies()[0],getMovies()[2])
    detailAppBar(title = "My Favorite Movies", onBackButton = {navController.popBackStack()}) {
        favoriteMoviesList(movieList = movies)
    }
}

@Composable
fun favoriteMoviesList(movieList:List<Movie>){
    LazyColumn(){
        items(movieList) {
            movie ->
            movieRow(movie = movie)
        }
    }
}