package com.carly.features.mycars.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carly.R
import com.carly.features.mycars.ui.dto.MyCar
import com.carly.ui.theme.CarlyTheme


@Composable
fun MyCarListItem(
    car: MyCar,
    isSelected: Boolean,
    onCarSelected: () -> Unit,
    onDeleteCar: () -> Unit
) {
    val roundedShape = remember { RoundedCornerShape(12.dp) }
    val borderColor =
        if (isSelected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.primaryContainer
    val borderWidth = if (isSelected) 1.dp else 0.5.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCarSelected() }
            .border(
                width = borderWidth, color = borderColor,
                shape = roundedShape
            )
            .background(
                MaterialTheme.colorScheme.background,
                shape = roundedShape
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = "${car.brandName} - ${car.seriesName}",
                    color = MaterialTheme.colorScheme.onSecondary,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "${car.buildYear} - ${car.fuelType}",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            if (!isSelected) {
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onDeleteCar() },
                    painter = painterResource(R.drawable.delete_car_image),
                    contentDescription = "Delete car",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }


        }
    }
}

@Preview
@Composable
fun MyCarListItemPreview() {
    CarlyTheme {
        MyCarListItem(
            car = MyCar(1, "BMW", "X5", 2022, "Petrol"),
            isSelected = true,
            onCarSelected = {},
            onDeleteCar = {}
        )
    }

}