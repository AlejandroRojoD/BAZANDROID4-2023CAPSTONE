package com.example.wizelineproject.presentation.ui.detailsmovie.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DescriptionContainer(
    modifier: Modifier,
    movieTitle: String = "",
    movieOverview: String = "",
    releaseDate: String = ""
) {
    Column(modifier = modifier) {
        Row() {
            Text(
                modifier = Modifier.width(95.dp),
                fontWeight = FontWeight.Bold,
                text = "Titulo",
                style = TextStyle(fontSize = 15.sp)
            )
            Text(
                maxLines = 2,
                text = movieTitle,
                style = TextStyle(fontSize = 15.sp)
            )
        }
        Row() {
            Text(
                modifier = Modifier.width(95.dp),
                fontWeight = FontWeight.Bold,
                text = "Sinopsis",
                style = TextStyle(fontSize = 15.sp)
            )
            Text(
                maxLines = 6,
                text = movieOverview,
                style = TextStyle(fontSize = 15.sp)
            )
        }
        Row() {
            Text(
                modifier = Modifier.width(95.dp),
                fontWeight = FontWeight.Bold,
                text = "Lanzamiento",
                style = TextStyle(fontSize = 15.sp)
            )
            Text(
                text = releaseDate,
                style = TextStyle(fontSize = 15.sp)
            )
        }
    }

}