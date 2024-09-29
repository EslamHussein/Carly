package com.carly.features.addcar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carly.core.ui.ListNextIcon
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
        ListNextIcon()
    }
}

@Preview
@Composable
fun CarSelectionListItemPreview() {
    CarlyTheme {
        CarSelectionListItem(item = SelectionItem(1, "BMW"), onClick = {})
    }
}