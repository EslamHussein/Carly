package com.carly.features.dashboard.rp

import com.carly.core.data.json.CarsJsonDataSource
import com.carly.core.data.local.CarsLocalDataSource
import com.carly.core.dispatcher.DispatcherProvider
import kotlinx.coroutines.withContext

class DashboardRepositoryImpl(
    private val localDataSource: CarsLocalDataSource,
    private val jsonDataSource: CarsJsonDataSource,
    private val dispatcherProvider: DispatcherProvider
) : DashboardRepository {
    override suspend fun loadInitData() = withContext(dispatcherProvider.io) {
        if (localDataSource.isDBEmpty()) {
            val carBrandList = jsonDataSource.getBrands()
            localDataSource.insertCarBrands(carBrandList.brands)
            localDataSource.addFuelTypes(carBrandList.fuelTypes)
        }
    }

    override suspend fun getSelectedCar(): String? {
        loadInitData()
        return null
    }

}