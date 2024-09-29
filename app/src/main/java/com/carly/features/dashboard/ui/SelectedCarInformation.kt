package com.carly.features.dashboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carly.R
import com.carly.features.dashboard.ui.dto.UserCar
import com.carly.ui.theme.CarlyTheme
import com.carly.ui.theme.Montserrat

@Composable
fun SelectedCarInformation(
    modifier: Modifier = Modifier,
    car: UserCar,
    onMenuClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "${car.brandName} - ${car.seriesName}".uppercase(),
                    style = MaterialTheme.typography.headlineMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 28.sp,
                        fontFamily = Montserrat,
                        fontWeight = FontWeight.Black
                    ),
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${car.buildYear} - ${car.fuelType}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondary,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light
                    ),
                    textAlign = TextAlign.Start
                )
            }


            Image(
                modifier = Modifier
                    .size(38.dp)
                    .clickable { onMenuClick() },
                painter = painterResource(id = R.drawable.switch_car_image),
                contentDescription = "Menu Icon",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable

private fun SelectedCarInformationPreview() {
    CarlyTheme {
        SelectedCarInformation(
            Modifier.background(Color.DarkGray),
            car = UserCar(12L, "Audi", "A4", 2007, "Gasoline")
        ) {

        }
    }
}
