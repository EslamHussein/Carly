package com.carly.features.dashboard.vm

import com.carly.core.data.local.entities.UserCarEntity

sealed interface DashboardState {
    data object Idle : DashboardState
    data object Loading : DashboardState
    data object NoCarAvailable : DashboardState
    data class CarSelected(val car: UserCarEntity) : DashboardState
}