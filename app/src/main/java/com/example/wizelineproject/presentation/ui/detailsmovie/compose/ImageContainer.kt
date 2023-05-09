package com.example.wizelineproject.presentation.ui.detailsmovie.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage

@Preview
@Composable
fun ImageContainer(
    modifier: Modifier = Modifier,
    imageUrl: String = ""
) {
    Column(modifier = modifier) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null
        )
    }
}