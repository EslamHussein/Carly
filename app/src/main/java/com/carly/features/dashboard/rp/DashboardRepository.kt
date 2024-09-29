package com.carly.features.dashboard.rp

import com.carly.features.dashboard.ui.dto.SelectedCarWithFeatures
import kotlinx.coroutines.flow.Flow

/**
 * Repository for the dashboard
 */
interface DashboardRepository {
    /**
     * load initial data
     */
    suspend fun loadInitData():Result<Unit>

    fun getSelectedCarWithFeatures(): Flow<SelectedCarWithFeatures?>
}