package com.example.newmovieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*

import com.example.newmovieapp.models.getMovies
import com.example.newmovieapp.ui.theme.NewMovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewMovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    MovieNavigation()
                    /*scaffoldLayout {
                        //mainContent(movieList = getMovies(), navController = navController)
                        //movieRow(movie = getMovies()[0])
                        MovieNavigation()
                    }*/
                }
            }
        }
    }
}
