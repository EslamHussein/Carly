package com.carly.features.dashboard.rp

interface DashboardRepository {
    suspend fun loadInitData()
    suspend fun getSelectedCar(): String?
}