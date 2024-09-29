package com.carly.features.dashboard.domain

import com.carly.features.dashboard.rp.DashboardRepository
import com.carly.features.dashboard.ui.dto.SelectedCarWithFeatures
import kotlinx.coroutines.flow.Flow

class GetSelectedCarUseCase(private val repository: DashboardRepository) {

    operator fun invoke(): Flow<SelectedCarWithFeatures?> {
        return repository
            .getSelectedCarWithFeatures()
    }
}