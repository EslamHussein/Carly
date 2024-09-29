package com.carly.features.addcar.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.carly.R
import com.carly.core.ui.GradientScreenBackground
import com.carly.core.ui.ListItemDivider
import com.carly.features.addcar.ui.appbar.CarlyAppBar
import com.carly.features.addcar.ui.appbar.SearchBar
import com.carly.features.addcar.vm.AddCarAction
import com.carly.features.addcar.vm.AddCarSideEffect
import com.carly.features.addcar.vm.AddCarState
import com.carly.features.addcar.vm.AddCarViewModel
import com.carly.features.addcar.vm.CreateUserCar
import com.carly.features.addcar.vm.SelectionItem
import com.carly.features.addcar.vm.Step
import com.carly.features.addcar.vm.getSearchHint
import com.carly.features.navigation.DashboardDestination
import com.carly.ui.theme.CarlyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddNewCarScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddCarViewModel = koinViewModel()
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    val context = LocalContext.current

    LaunchedEffect(Unit) {

        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                AddCarSideEffect.NavigateBack -> {
                    navController.popBackStack()
                }

                AddCarSideEffect.NativeToHome -> {
                    navController.navigate(DashboardDestination) {
                        popUpTo(DashboardDestination) {
                            inclusive = true
                        }
                    }
                }

                AddCarSideEffect.ShowError -> {

                    Toast.makeText(
                        context,
                        context.getString(R.string.ops_something_went_wong), Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }

    }
    LaunchedEffect(key1 = Unit) {
        viewModel.sendAction(AddCarAction.LoadData(state.currentStep))
    }
    BackHandler { viewModel.sendAction(AddCarAction.BackPressed) }
    AddNewCarScreen(
        modifier = modifier, state = state, onAction = { action ->
            viewModel.sendAction(action)
        }
    )
}


@Composable
fun AddNewCarScreen(
    modifier: Modifier = Modifier,
    state: AddCarState,
    onAction: (AddCarAction) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = GradientScreenBackground()
            )
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        CarlyAppBar(
            title = stringResource(R.string.car_selection),
            onBackClick = { onAction(AddCarAction.BackPressed) }
        )
        SearchBar(modifier = Modifier.padding(horizontal = 18.dp),
            searchQuery = state.searchQuery,
            placeholderText = state.currentStep.getSearchHint(),
            onSearchQueryChanged = { query ->
                onAction(AddCarAction.SearchQueryChanged(query))
            })
        Text(
            text = state.newCar.toFormattedString(),
            style = MaterialTheme.typography.bodyMedium
                .copy(color = MaterialTheme.colorScheme.secondary),
            modifier = Modifier.padding(16.dp),
        )

        ListItemDivider()
        LazyColumn {

            items(state.filtered, key = { it.hashCode() }) { item ->
                CarSelectionListItem(item = item,
                    onClick = { onAction(AddCarAction.OnItemSelected(item)) })
                ListItemDivider()
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CarSelectionScreenPreview() {
    CarlyTheme {
        AddNewCarScreen(
            state = AddCarState(newCar = CreateUserCar(brand = SelectionItem(1, "BMW"))),
            onAction = {})
    }
}
