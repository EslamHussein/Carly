package com.carly.features.dashboard.domain

import com.carly.features.dashboard.rp.DashboardRepository

class GetSelectedCarUseCase(private val repository: DashboardRepository) {

    suspend operator fun invoke(): String? {
        return repository.getSelectedCar()
    }
}