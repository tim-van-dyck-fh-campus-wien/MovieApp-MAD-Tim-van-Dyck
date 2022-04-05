package com.example.newmovieapp

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.newmovieapp.models.Movie

class FavoritesViewModel: ViewModel() {
    //Private , nicht bearbeitbar von ausen
    private val _favoriteMovies = mutableStateListOf<Movie>()
    val favoriteMovies:List<Movie>//immutable
        get()=_favoriteMovies
    fun addMovie(movie:Movie){
        Log.i("favoriteVM",movie.title)
        _favoriteMovies.add(movie)
        Log.i("favoriteVM",_favoriteMovies.get(0).title)

    }
    fun removeMovie(movie:Movie){
        _favoriteMovies.remove(movie)
    }
    fun getAllMovies():List<Movie>{
        return _favoriteMovies
    }
    fun checkIfMovieIsFavorite(movie:Movie): Boolean {
        if(_favoriteMovies.find { it.id==movie.id }==null){
            return false
        }
        return true
    }
    //add movie to favorites
    //remove movie from favorites
    //get all movies from favorites
    //check if a movie is already a favorite
}