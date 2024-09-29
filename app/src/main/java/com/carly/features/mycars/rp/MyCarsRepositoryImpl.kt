package com.carly.features.mycars.rp

import com.carly.core.data.datastore.CarDataStoreSource
import com.carly.core.data.local.CarsLocalDataSource
import com.carly.core.dispatcher.DispatcherProvider
import com.carly.core.mapper.toMyCar
import com.carly.features.mycars.ui.dto.MyCar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MyCarsRepositoryImpl(
    private val localDataSource: CarsLocalDataSource,
    private val dataStoreSource: CarDataStoreSource,
    private val dispatcherProvider: DispatcherProvider
) : MyCarsRepository {
    override fun getMyCars(): Flow<List<MyCar>> {
        return localDataSource
            .getAllUserCars()
            .map { list ->
                list.map { car ->
                    car.toMyCar()
                }
            }
            .flowOn(dispatcherProvider.io)
    }

    override fun getSelectedCarId(): Flow<Long?> {
        return dataStoreSource.getSelectedCarId().flowOn(dispatcherProvider.io)
    }


    override suspend fun deleteCar(myCar: MyCar): Result<Unit> =
        withContext(dispatcherProvider.io) {
            localDataSource.deleteUserCarById(myCar.id)
        }

    override suspend fun setSelectedCarId(carId: Long): Result<Unit> =
        withContext(dispatcherProvider.io) {
            Result.success(dataStoreSource.setSelectedCarId(carId))
        }
}