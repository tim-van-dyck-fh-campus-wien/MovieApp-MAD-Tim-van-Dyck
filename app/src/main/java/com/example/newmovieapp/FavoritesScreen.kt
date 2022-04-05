package com.example.newmovieapp

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.newmovieapp.models.Movie

@Composable
fun FavoritesScreen(navController: NavController, favoritesVM: FavoritesViewModel){

    //var movies= listOf<Movie>(getMovies()[0],getMovies()[2])
    var movies = favoritesVM.getAllMovies()
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