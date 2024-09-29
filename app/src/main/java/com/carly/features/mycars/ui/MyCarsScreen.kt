package com.carly.features.mycars.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.carly.R
import com.carly.core.ui.GradientScreenBackground
import com.carly.features.addcar.ui.appbar.CarlyAppBar
import com.carly.features.mycars.ui.dto.MyCar
import com.carly.features.mycars.vm.MyCarsAction
import com.carly.features.mycars.vm.MyCarsSideEffect
import com.carly.features.mycars.vm.MyCarsState
import com.carly.features.mycars.vm.MyCarsViewModel
import com.carly.features.navigation.AddNewCarDestination
import com.carly.ui.theme.CarlyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MyCarsScreen(navController: NavController, viewModel: MyCarsViewModel = koinViewModel()) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.sendAction(MyCarsAction.LoadCars)
    }
    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                MyCarsSideEffect.NavigateToAddNewCar -> navController.navigate(AddNewCarDestination)
                MyCarsSideEffect.ShowError -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.ops_something_went_wong), Toast.LENGTH_SHORT
                    ).show()
                }

                MyCarsSideEffect.NavigateToBack -> navController.popBackStack()
            }
        }
    }


    MyCarsScreen(viewModel.state.collectAsState().value) { action ->
        viewModel.sendAction(action)
    }
}

@Composable
fun MyCarsScreen(state: MyCarsState, onAction: (MyCarsAction) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                GradientScreenBackground()
            )
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        CarlyAppBar(
            title = stringResource(R.string.your_cars),
            onBackClick = { onAction(MyCarsAction.OnBackClicked) }
        )

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.cars, key = { car -> car.hashCode() }) { car ->
                MyCarListItem(
                    car = car,
                    isSelected = car.id == state.selectedCarId,
                    onCarSelected = { onAction(MyCarsAction.OnCarSelected(car)) },
                    onDeleteCar = { onAction(MyCarsAction.OnDeleteCarClicked(car)) })
            }
        }

        AddNewCarButton(
            modifier = Modifier.padding(
                horizontal = 24.dp,
                vertical = 12.dp
            )
        ) { onAction(MyCarsAction.OnAddNewCarClicked) }
    }

}

@Preview
@Composable
private fun MyCarsScreenPreview() {
    CarlyTheme {
        MyCarsScreen(
            state = MyCarsState(
                cars = listOf(
                    MyCar(1, "BMW", "X5", 2019, "GAS"),
                    MyCar(2, "BMW", "X5", 2019, "GAS"),
                    MyCar(3, "BMW", "X5", 2019, "GAS"),
                    MyCar(4, "BMW", "X5", 2019, "GAS"),
                    MyCar(5, "BMW", "X5", 2019, "GAS"),
                    MyCar(6, "BMW", "X5", 2019, "GAS"),
                    MyCar(7, "BMW", "X5", 2019, "GAS"),
                    MyCar(8, "BMW", "X5", 2019, "GAS"),
                    MyCar(9, "BMW", "X5", 2019, "GAS"),
                    MyCar(10, "BMW", "X5", 2019, "GAS"),
                    MyCar(11, "BMW", "X5", 2019, "GAS"),
                    MyCar(12, "last", "X5", 2019, "GAS")
                )
            )
        ) { }
    }
}