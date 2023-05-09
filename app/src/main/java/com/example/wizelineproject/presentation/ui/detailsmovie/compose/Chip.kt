package com.example.wizelineproject.presentation.ui.detailsmovie.compose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Chip(
    category: String
) {
    Surface(
        modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
        shadowElevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.secondary
    ) {
        Row() {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}