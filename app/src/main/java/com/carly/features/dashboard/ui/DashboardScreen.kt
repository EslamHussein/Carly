package com.carly.features.dashboard.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.carly.R
import com.carly.features.dashboard.vm.DashboardState
import com.carly.features.dashboard.vm.DashboardViewModel
import com.carly.features.navigation.CarSelectionDestination
import com.carly.ui.theme.CarlyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: DashboardViewModel = koinViewModel()
) {
    DashboardScreen(
        modifier = modifier,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        onAddCarCLick = { navController.navigate(CarSelectionDestination) }
    )
}


@Composable
private fun DashboardScreen(
    modifier: Modifier = Modifier,
    state: DashboardState,
    onAddCarCLick: () -> Unit
) {

    Column(
        modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.dashboard_background_image),
                contentScale = ContentScale.FillBounds
            ),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Icon(
            modifier = Modifier.size(150.dp),
            painter = painterResource(id = R.drawable.company_image),
            contentDescription = "Company Image"
        )
        when (state) {
            is DashboardState.NoCarAvailable -> {
                AddNewCar(onAddCarClick = onAddCarCLick)
            }

            DashboardState.CarSelected -> {
                DashboardWithSelectedCar()
            }

            DashboardState.Idle -> TODO()
            DashboardState.Loading -> {
                CircularProgressIndicator()
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun DashboardScreenPreview() {
    CarlyTheme {
        DashboardScreen(state = DashboardState.Loading){}
    }

}
