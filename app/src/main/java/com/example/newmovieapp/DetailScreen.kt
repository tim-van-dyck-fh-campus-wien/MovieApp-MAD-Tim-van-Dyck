package com.example.newmovieapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.newmovieapp.models.Movie
import com.example.newmovieapp.models.getMovies

@Composable
fun DetailScreen(movieId:String?,navController: NavController){
    var movie = getMovies().find{it.id==movieId}
    if(movie!=null){
        detailAppBar(title = movie.title, onBackButton = {navController.popBackStack()}) {
            Column{
                movieRow(movie = movie)
                movieImages(images = movie.images)
            }
        }
    }else{
        detailAppBar(title="Error",onBackButton = {navController.popBackStack()}){
            Text(text = "We could not load the movie!")
        }
    }
    /*Column() {
        Text(text="${movie?.title}",style= MaterialTheme.typography.h6)
        Text(text="Director: ${movie?.director}",style= MaterialTheme.typography.caption)
        Text(text="Released: ${movie?.year}",style= MaterialTheme.typography.caption)
        Text(text="Plot: ${movie?.plot}",style= MaterialTheme.typography.caption)
        Divider()
        Text(text="Actors: ${movie?.actors}",style= MaterialTheme.typography.caption)
        Divider()
        Text(text="Genre: ${movie?.genre}",style= MaterialTheme.typography.caption)
        Divider()
        Text(text="Rating: ${movie?.rating}",style= MaterialTheme.typography.caption)
    }*/

}

@Composable
fun movieImages(images:List<String>) {
    Card(
        modifier = Modifier
            .padding(12.dp)
            ,
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
    ) {
        Column(){
           Text(text = "Movie Images",textAlign = TextAlign.Center
               ,style =MaterialTheme.typography.h5)
            LazyRow{
                items(images){image ->
                    movieImage(path = image)
                }
            }
        }
       
    }
}
@Composable
fun movieImage(path:String){
    AsyncImage(model = path, contentDescription = "a image of a movie",
        contentScale = ContentScale.Crop, modifier = Modifier
        .size(300.dp)
        .padding(7.dp)
        .clip(
            RoundedCornerShape(5.dp)
        ))
    //Text(path)
}