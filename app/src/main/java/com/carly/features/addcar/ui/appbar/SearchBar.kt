package com.carly.features.addcar.ui.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carly.R
import com.carly.ui.theme.CarlyTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchQuery: String,
    placeholderText: String = stringResource(R.string.search_for_brand),
    onSearchQueryChanged: (String) -> Unit
) {

    OutlinedTextField(
        value = searchQuery,
        onValueChange = {
            onSearchQueryChanged(it)
        },
        placeholder = {
            Text(
                text = placeholderText,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = AppTextInputColors,
        modifier = modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp)),
        shape = RoundedCornerShape(4.dp),
        singleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    CarlyTheme {
        SearchBar(
            searchQuery = "Search Query",
            onSearchQueryChanged = {}
        )
    }
}



