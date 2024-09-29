package com.carly.features.dashboard.rp

import com.carly.core.data.local.entities.UserCarEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the dashboard
 */
interface DashboardRepository {
    /**
     * load initial data
     */
    suspend fun loadInitData()

    /**
     * Get selected car
     */
    suspend fun getSelectedCar(): Flow<UserCarEntity?>
}