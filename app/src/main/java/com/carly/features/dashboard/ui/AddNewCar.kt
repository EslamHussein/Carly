package com.carly.features.dashboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carly.R
import com.carly.ui.theme.CarlyTheme

@Composable

fun AddNewCar(modifier: Modifier = Modifier, onAddCarClick: () -> Unit) {
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        // fixme Icon is not transparent
        Image(
            modifier = Modifier
                .padding(top = 100.dp)
                .size(100.dp)
                .clickable { onAddCarClick() },
            painter = painterResource(id = R.drawable.add_car_image),
            contentDescription = "Add New Car Image"
        )
    }
}


@Preview(showBackground = true)
@Composable
fun AddNewCarPreview() {
    CarlyTheme {
        AddNewCar {}
    }
}