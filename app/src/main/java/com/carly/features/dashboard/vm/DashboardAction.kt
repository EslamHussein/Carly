package com.carly.features.dashboard.vm

sealed interface DashboardAction {
    data object LoadInitData : DashboardAction
    data object LoadSelectedCar : DashboardAction
    data object OnMenuClicked : DashboardAction
    data object OnAddNewCarClicked : DashboardAction
}