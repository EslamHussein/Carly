package com.carly.features.dashboard.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.carly.R
import com.carly.core.ui.AllScreenSizesPreview
import com.carly.features.dashboard.ui.dto.SelectedCarWithFeatures
import com.carly.features.dashboard.ui.dto.SupportedFeature
import com.carly.features.dashboard.ui.dto.UserCar
import com.carly.features.dashboard.vm.DashboardAction
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
    LaunchedEffect(Unit) { viewModel.sendAction(DashboardAction.LoadInitData) }
    DashboardScreen(
        modifier = modifier
            .systemBarsPadding(),
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

    ConstraintLayout(
        modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
    ) {
        val (carlyLogo, carInformation, centerImage, supportedFeatureSection) = createRefs()
        Image(
            painter = painterResource(id = R.drawable.dashboard_background_image),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Icon(
            modifier = Modifier
                .constrainAs(carlyLogo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(150.dp)
                .padding(top = 8.dp),
            painter = painterResource(id = R.drawable.company_image),
            contentDescription = "Company Image",
            tint = MaterialTheme.colorScheme.onSecondary
        )

        state.selectedCarWithFeatures?.car?.let {
            SelectedCarInformation(
                modifier = Modifier.constrainAs(carInformation) {
                    bottom.linkTo(centerImage.top)
                    top.linkTo(carlyLogo.bottom)
                    height = Dimension.fillToConstraints
                },
                car = state.selectedCarWithFeatures.car,
                onMenuClick = { /*TODO*/ }
            )
        }

        val centerImageIconResourceId = if (state.selectedCarWithFeatures?.car != null) {
            R.drawable.car_image
        } else {
            R.drawable.add_car_image
        }

        Image(
            painter = painterResource(id = centerImageIconResourceId),
            contentDescription = "car image",
            modifier = Modifier
                .then(
                    if (state.selectedCarWithFeatures == null) Modifier.clickable { onAddCarCLick() }
                    else Modifier
                )
                .size(150.dp)
                .constrainAs(centerImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                    height = Dimension.preferredWrapContent
                }
        )
        state.selectedCarWithFeatures?.features?.let {
            SupportedFeatureSection(
                modifier = Modifier
                    .constrainAs(supportedFeatureSection) {
                        top.linkTo(centerImage.bottom)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                        height = Dimension.fillToConstraints
                    }
                    .padding(bottom = 24.dp),
                features = state.selectedCarWithFeatures.features
            )
        }
    }

}

@AllScreenSizesPreview
@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun DashboardScreenPreview() {
    CarlyTheme {
        DashboardScreen(
            state = DashboardState(
                SelectedCarWithFeatures(
                    car = UserCar(
                        id = 12L,
                        brandName = "BMW",
                        seriesName = "X6",
                        buildYear = 2023,
                        fuelType = "Electric"
                    ),
                    features = listOf(
                        SupportedFeature(1, "Feature 1"),
                        SupportedFeature(2, "Feature 2"),
                        SupportedFeature(3, "Feature 3"),
                        SupportedFeature(4, "Feature 4"),
                        SupportedFeature(5, "Feature 5")
                    )
                )
            )
        ) {}
    }
}
