package com.example.newmovieapp

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.newmovieapp.models.Movie

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun movieRow(//as function is favorited
    movie: Movie, onItemClick:(String)->Unit={},content:@Composable()()->Unit={}){
    var infoIsExpanded by remember{ mutableStateOf(false) }
    Surface(){
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(onClick = {
                onItemClick(movie.id)
            }),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = 7.dp
        ){
            Row(modifier = Modifier
                .padding(12.dp)
                .shadow(3.dp), verticalAlignment = Alignment.CenterVertically) {

               /* Icon(
                    Icons.Rounded.AccountCircle, contentDescription = "Localized description",modifier = Modifier
                        .height(148.dp)
                        .width(148.dp)
                        .padding(9.dp)
                        .shadow(3.dp))*/
                AsyncImage(model=movie.images[0], contentDescription = "The movies title image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(148.dp)
                        .padding(9.dp)
                        .clip(
                            RoundedCornerShape(50)
                        ))
                val favoritesViewModel:FavoritesViewModel = viewModel()
                
                Column(modifier = Modifier.width(150.dp)){
                    Text(text="${movie.title}",style= MaterialTheme.typography.h6)
                    Text(text="Director: ${movie.director}",style= MaterialTheme.typography.caption)
                    Text(text="Released: ${movie.year}",style= MaterialTheme.typography.caption)

                    AnimatedVisibility(visible = infoIsExpanded, modifier = Modifier.padding(4.dp)) {
                        Column {
                            Text(text="Plot: ${movie.plot}",style= MaterialTheme.typography.caption)
                            Divider()
                            Text(text="Actors: ${movie.actors}",style= MaterialTheme.typography.caption)
                            Divider()
                            Text(text="Genre: ${movie.genre}",style= MaterialTheme.typography.caption)
                            Divider()
                            Text(text="Rating: ${movie.rating}",style= MaterialTheme.typography.caption)
                        }
                    }
                    IconButton(onClick = {infoIsExpanded=!infoIsExpanded}){
                        if(!infoIsExpanded){
                            Icon(Icons.Default.KeyboardArrowDown, "expand" )
                        }else{
                            Icon(Icons.Default.KeyboardArrowUp, "collapse" )
                        }
                    }


                }
                content()

            }
        }
    }
}
@Composable
fun favoriteIconButton(isFavorited:Boolean=false, addToFavorite:()->Unit={}, removeFromFavorite:()->Unit={}){

    if(!isFavorited){
        IconButton(onClick={addToFavorite()}){
            Icon(Icons.Default.FavoriteBorder,"Favorite this movie", tint=Color.Red)
        }
    }else{
        IconButton(onClick={removeFromFavorite()}){
            Icon(Icons.Filled.Favorite,"Remove this movie from favorites", tint=Color.Red)
        }
    }
}
@Composable
fun detailAppBar(title:String,onBackButton:()->Unit={},content:@Composable() () -> Unit){
    Scaffold(
        topBar = {
            TopAppBar(
                title={
                    Text(
                        text="$title",
                        color= Color.White,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackButton) {
                        Icon(Icons.Filled.ArrowBack,"back Icon")
                    }
                }
            )
        }

    ) {
        content()
    }
}
