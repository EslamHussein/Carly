package com.carly.features.dashboard.vm

sealed interface DashboardSideEffect {
    data object ShowError : DashboardSideEffect
    data object NavigateToAddNewCar : DashboardSideEffect
    data object NavigateToCarList : DashboardSideEffect
}