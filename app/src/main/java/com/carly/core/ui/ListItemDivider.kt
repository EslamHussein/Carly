package com.carly.core.ui

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ListItemDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(modifier = modifier, color = Color.Gray, thickness = 0.5.dp)
}