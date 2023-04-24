package com.example.wizelineproject.presentation.ui.detailsmovie.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wizelineproject.domain.entities.Movie

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    selectedMovie: Movie
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
                    .height(300.dp)
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 0.dp, vertical = 20.dp)
            )
            DescriptionContainer(
                modifier = Modifier.padding(10.dp),
                movieTitle = selectedMovie.title,
                movieOverview = selectedMovie.overview,
                releaseDate = selectedMovie.releaseDate
            )
        }
    }

}
