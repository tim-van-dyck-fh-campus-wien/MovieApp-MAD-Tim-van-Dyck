package com.example.newmovieapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.newmovieapp.models.Movie
import com.example.newmovieapp.models.getMovies
import com.example.newmovieapp.ui.theme.NewMovieAppTheme

val defaultList = listOf("test","second","third","fourth","fifth","sixth")
@Composable
fun dropDownMenu(isExpanded:Boolean,onDismiss:(Boolean)->Unit, favoritesClicked:()->Unit={}){
    DropdownMenu(expanded =isExpanded , onDismissRequest = {onDismiss(false)}) {
        DropdownMenuItem(onClick = favoritesClicked) {
            Row() {
                Icon(Icons.Default.Favorite,"Favorite")
                Text(text = "Favorites")
            }
        }
    }

}
@Composable
fun HomeScreen(navController: NavController, favoritesVM: FavoritesViewModel){
    scaffoldLayout(favoritesClicked = {navController.navigate("favoritesscreen")}) {
        mainContent(movieList = getMovies(), navController = navController,favoritesVM = favoritesVM)
    }
}


@Composable
fun scaffoldLayout(favoritesClicked: () -> Unit,content:@Composable() () -> Unit){
    var dropdownIsExpanded by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title={
                    Text(
                        text="Movies",
                        color= Color.White,
                    )
                },
                actions={
                    Box(
                        Modifier
                            .wrapContentSize(Alignment.TopEnd)
                            .fillMaxHeight()
                    ) {
                        IconButton(onClick = { dropdownIsExpanded = !dropdownIsExpanded }) {
                            Icon(Icons.Default.MoreVert, "asdf")
                        }
                        dropDownMenu(isExpanded = dropdownIsExpanded,onDismiss = {dropdownIsExpanded=it}, favoritesClicked = favoritesClicked)
                    }
                }
            )
        }

    ) {
        content()



    }
}

@Composable
fun mainContent(movieList: List<Movie>, navController: NavController, favoritesVM: FavoritesViewModel){
    val movieState = remember{favoritesVM.checkIfMovieIsFavorite(movieList[0])}
    LazyColumn{
        items(movieList) { movie -> movieRow(movie,movieIsFavorited = {favoritesVM.checkIfMovieIsFavorite(it)}, movieFavorited = { favoritesVM.addMovie(it)}, movieRemovedFavorites = {favoritesVM.removeMovie(it)}){
            movieId->
            navController.navigate(route="detailscreen/$movieId")
        } }
    }
}

//@Preview(showBackground = true)



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NewMovieAppTheme {
        //mainContent(movieList = getMovies(), navController = navController)
    }
}