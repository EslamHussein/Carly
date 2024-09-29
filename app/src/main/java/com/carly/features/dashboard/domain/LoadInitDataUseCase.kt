package com.carly.features.dashboard.domain

import com.carly.features.dashboard.rp.DashboardRepository

class LoadInitDataUseCase(private val repository: DashboardRepository) {

    suspend operator fun invoke(): Result<Unit> {
        return repository.loadInitData()
    }
}