package com.example.newmovieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*

import com.example.newmovieapp.models.getMovies
import com.example.newmovieapp.ui.theme.NewMovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onDestroy() {
        super.onDestroy()
        Log.i("mainAct","onDestroy")

    }

    override fun onStart() {
        super.onStart()
        Log.i("mainAct","onStart")

    }

    override fun onRestart() {
        super.onRestart()
        Log.i("mainAct","onRestart")

    }

    override fun onStop() {
        super.onStop()
        Log.i("mainAct","onStop")

    }

    override fun onPause() {
        super.onPause()
        Log.i("mainAct","onPause")

    }

    override fun onResume() {//Läuft bis die app beendet wird/gepaused wird
        super.onResume()
        Log.i("mainAct","onResume")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("mainAct","onCreate")
        //Composables = View -> Soll keine logik quasi ham, auser, falls das gemacht, rufe des aus view model auf.
        //Dataclasses = Models. Kann z.b. auch von DB Lesen, local und remote.
        //Business Logic -> View Model, Datenverarbeitung... Jeglicher mehrwert in der App, welcher nicht nur
        //Datenspeicherung und Display ist.
        /*View Model -> View steht in kontakt mit View Model.
         Verbindung zwischen View und Model. View Model kenn View, View View Model nicht.
         Wiederverwendbar. Observable, damit view merkt wenn sich was ändert. Observiert das Model.
         Ist life cycle aware! Nix geht verloren falls z.B: app verlassen wird...
         (Configuration Changes) aber nix falls komplett geschlossen
        */
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
