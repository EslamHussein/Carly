package com.carly.features.addcar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.carly.R
import com.carly.features.addcar.ui.appbar.CarlyAppBar
import com.carly.features.addcar.ui.appbar.SearchBar
import com.carly.features.addcar.vm.AddCarAction
import com.carly.features.addcar.vm.AddCarState
import com.carly.features.addcar.vm.AddCarViewModel
import com.carly.features.addcar.vm.getSearchHint
import com.carly.ui.theme.CarlyTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddNewCarScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AddCarViewModel = koinViewModel()
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value

    val sideEffect = viewModel.sideEffect.collectAsState(null).value // Observe side effects

    LaunchedEffect(sideEffect) {
        when (sideEffect) {
            com.carly.features.addcar.vm.AddCarSideEffect.NavigateBack -> {
                navController.popBackStack()
            }

            null -> {}
        }

    }
    // Collect side effects in LaunchedEffect
    LaunchedEffect(key1 = Unit) {
        viewModel.sendAction(AddCarAction.LoadData(state.currentStep))
    }
    AddNewCarScreen(
        modifier = modifier,
        state = state,
        onAction = { action ->
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
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xff3b404a), // Start color (top)
                        Color(0xFF25272d)  // End color (bottom)
                    )
                )
            )
    ) {
        CarlyAppBar(
            title = stringResource(R.string.car_selection),
            onBackClick = { onAction(AddCarAction.BackPressed) }
        )
        // TODO search bar could be in a better design
        SearchBar(
            modifier = Modifier.padding(horizontal = 18.dp),
            searchQuery = state.searchQuery,
            placeholderText = state.currentStep.getSearchHint(),
            onSearchQueryChanged = { query ->
                onAction(AddCarAction.SearchQueryChanged(query))
            }
        )
        Text(
            text = state.newCar.toFormattedString(),//TODO check recomposition
            style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFFFFA000)), // Orange color
            modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
        )


        HorizontalDivider(color = Color.Gray, thickness = 0.5.dp) // Line separator

        // List of car series
        LazyColumn {

            items(state.filtered, key = { it.hashCode() }) { item ->
                CarSelectionListItem(
                    item = item,
                    onClick = { onAction(AddCarAction.OnItemSelected(item)) })
                HorizontalDivider(color = Color.Gray, thickness = 0.5.dp) // Line separator
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CarSelectionScreenPreview() {
    CarlyTheme {
        AddNewCarScreen(state = AddCarState(), onAction = {})
    }
}
