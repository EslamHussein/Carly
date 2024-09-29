package com.carly.features.addcar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carly.core.ui.innerShadow
import com.carly.features.addcar.vm.SelectionItem
import com.carly.ui.theme.CarlyTheme


@Composable
fun CarSelectionListItem(modifier: Modifier = Modifier, item: SelectionItem, onClick: () -> Unit) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = item.name, color = MaterialTheme.colorScheme.onPrimary)
        Icon(
            modifier = Modifier
                .size(32.dp)
                .innerShadow(
                    shape = CircleShape,
                    color = Color.Black,
                    offsetY = 1.dp,
                    offsetX = 1.dp,
                    spread = 1.dp
                )
                .border(
                    width = 0.2.dp,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape
                )
                .background(color = MaterialTheme.colorScheme.background, shape = CircleShape),
            imageVector = Icons.Default.ChevronRight,
            contentDescription = "Next",
            tint = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}

@Preview
@Composable
fun CarSelectionListItemPreview() {
    CarlyTheme {
        CarSelectionListItem(item = SelectionItem(1, "BMW"), onClick = {})
    }
}