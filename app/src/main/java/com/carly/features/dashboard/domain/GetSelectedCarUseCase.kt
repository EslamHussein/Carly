package com.carly.features.dashboard.domain

import com.carly.core.data.local.entities.UserCarEntity
import com.carly.features.dashboard.rp.DashboardRepository
import kotlinx.coroutines.flow.Flow

class GetSelectedCarUseCase(private val repository: DashboardRepository) {

    suspend operator fun invoke(): Flow<UserCarEntity?> {
        return repository.getSelectedCar()
    }
}