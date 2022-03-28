package com.example.newmovieapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newmovieapp.models.Movie

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun movieRow(movie: Movie, onItemClick:(String)->Unit={}){
    var infoIsExpanded by remember{ mutableStateOf(false) }
    Surface(){
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp).clickable(onClick = {
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
                    modifier = Modifier.size(148.dp).padding(9.dp).clip(
                    RoundedCornerShape(50)))
                Column(){
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


            }
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
