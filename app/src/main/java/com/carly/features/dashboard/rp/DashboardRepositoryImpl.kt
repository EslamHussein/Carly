package com.carly.features.dashboard.rp

import com.carly.core.asFailure
import com.carly.core.data.datastore.CarDataStoreSource
import com.carly.core.data.json.CarsJsonDataSource
import com.carly.core.data.local.CarsLocalDataSource
import com.carly.core.dispatcher.DispatcherProvider
import com.carly.core.mapper.toSelectedCarWithFeatures
import com.carly.features.dashboard.ui.dto.SelectedCarWithFeatures
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DashboardRepositoryImpl(
    private val localDataSource: CarsLocalDataSource,
    private val jsonDataSource: CarsJsonDataSource,
    private val dataStoreSource: CarDataStoreSource,
    private val dispatcherProvider: DispatcherProvider
) : DashboardRepository {
    override suspend fun loadInitData(): Result<Unit> = withContext(dispatcherProvider.io) {
        runCatching {
            if (localDataSource.isDBEmpty()) {
                val carBrandList = jsonDataSource.getBrands()
                localDataSource.insertCarBrands(carBrandList.brands)
                localDataSource.addFuelTypes(carBrandList.fuelTypes)
            }
        }.onFailure {
            it.asFailure<Throwable>()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getSelectedCarWithFeatures(): Flow<SelectedCarWithFeatures?> {
        return dataStoreSource.getSelectedCarId()
            .flatMapLatest { id ->
                when {
                    id == null || id <= 0L -> flowOf(null)
                    else -> localDataSource.getUserCarByIdWithSupportedFeatures(id)
                }
            }
            .map { entity -> entity?.toSelectedCarWithFeatures() }
            .flowOn(dispatcherProvider.io)
    }

}