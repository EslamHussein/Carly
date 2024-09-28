package com.carly.features.dashboard.domain

import com.carly.features.dashboard.rp.DashboardRepository

//TODO sould be removed
class LoadInitDataUseCase(private val repository: DashboardRepository) {

    suspend operator fun invoke() {
        repository.loadInitData()
    }
}