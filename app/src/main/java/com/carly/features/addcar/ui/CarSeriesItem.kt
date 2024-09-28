package com.carly.features.addcar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carly.features.addcar.vm.SelectionItem
import com.carly.ui.theme.CarlyTheme

@Composable
fun CarSelectionListItem(modifier: Modifier = Modifier, item: SelectionItem, onClick: () -> Unit) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(vertical = 16.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = item.name, color = MaterialTheme.colorScheme.onPrimary)
        Icon(Icons.Default.ChevronRight, contentDescription = "Next", tint = Color.Gray)
    }
}

@Preview
@Composable
fun CarSelectionListItemPreview() {
    CarlyTheme {
        CarSelectionListItem(item = SelectionItem(1, "BMW"), onClick = {})
    }
}