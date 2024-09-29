package com.carly.features.dashboard.rp

import com.carly.core.data.datastore.CarDataStoreSource
import com.carly.core.data.json.CarsJsonDataSource
import com.carly.core.data.local.CarsLocalDataSource
import com.carly.core.data.local.entities.UserCarEntity
import com.carly.core.dispatcher.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext

class DashboardRepositoryImpl(
    private val localDataSource: CarsLocalDataSource,
    private val jsonDataSource: CarsJsonDataSource,
    private val dataStoreSource: CarDataStoreSource,
    private val dispatcherProvider: DispatcherProvider
) : DashboardRepository {
    override suspend fun loadInitData() = withContext(dispatcherProvider.io) {
        if (localDataSource.isDBEmpty()) {
            val carBrandList = jsonDataSource.getBrands()
            localDataSource.insertCarBrands(carBrandList.brands)
            localDataSource.addFuelTypes(carBrandList.fuelTypes)
        }
    }

    override suspend fun getSelectedCar(): Flow<UserCarEntity?> {
        return dataStoreSource.getSelectedCarId()
            .onStart {
                loadInitData()
            }.map { id ->
                if (id == null || id <= 0L) { // if the id is null or less than 0, return null
                    return@map null
                }
                localDataSource.getUserCarById(id)
            }.flowOn(dispatcherProvider.io)
    }

}