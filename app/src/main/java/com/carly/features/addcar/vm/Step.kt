package com.carly.features.addcar.vm

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.carly.R

sealed interface Step {
    data object SelectBrand : Step
    data object SelectSeries : Step
    data object SelectBuildYear : Step
    data object SelectFuelType : Step
}



@Composable
fun Step.getSearchHint(): String {
    return when (this) {
        Step.SelectBrand -> stringResource(R.string.search_for_brand)
        Step.SelectBuildYear -> stringResource(R.string.search_for_build_year)
        Step.SelectFuelType -> stringResource(R.string.search_for_fuel_type)
        Step.SelectSeries -> stringResource(R.string.search_for_series)
    }
}