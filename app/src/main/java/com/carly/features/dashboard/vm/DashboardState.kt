package com.carly.features.dashboard.vm

sealed interface DashboardState {
    data object Idle : DashboardState
    data object Loading : DashboardState
    data object NoCarAvailable : DashboardState
    data object CarSelected : DashboardState
}