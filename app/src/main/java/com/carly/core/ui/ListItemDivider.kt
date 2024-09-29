package com.carly.core.ui

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carly.ui.theme.CarlyTheme

@Composable
fun ListItemDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        thickness = 1.4.dp
    )
}

@Preview
@Composable
fun ListItemDividerPreview() {
    CarlyTheme {
        ListItemDivider()
    }
}