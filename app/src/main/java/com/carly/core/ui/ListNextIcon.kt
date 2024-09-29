package com.carly.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carly.ui.theme.CarlyTheme

@Composable
fun ListNextIcon(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier
            .padding(8.dp)
            .border(
                width = 0.2.dp,
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = CircleShape
            )
            .background(color = MaterialTheme.colorScheme.background, shape = CircleShape)
            .innerShadow(
                shape = CircleShape,
                offsetY = 0.dp,
                offsetX = 0.dp,
                spread = 2.dp,
                blur = 0.dp
            ),
        imageVector = Icons.Default.ChevronRight,
        contentDescription = "Next",
        tint = MaterialTheme.colorScheme.surfaceVariant
    )
}


@Preview
@Composable
fun ListNextIconPreview() {
    CarlyTheme {
        ListNextIcon()
    }
}