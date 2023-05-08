package com.example.wizelineproject.presentation.ui.detailsmovie.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.local.entities.Movie

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    selectedMovie: com.example.local.entities.Movie,
    genresList: List<String>
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(10.dp),
        shadowElevation = 8.dp
    ) {
        Box(modifier = modifier) {
            ImageContainer(
                imageUrl = selectedMovie.posterUrl,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
            )
            DescriptionContainer(
                modifier = Modifier.padding(10.dp),
                movieTitle = selectedMovie.title,
                movieOverview = selectedMovie.overview,
                releaseDate = selectedMovie.releaseDate,
                genresList = genresList
            )
        }
    }

}
