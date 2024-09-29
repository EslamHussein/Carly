package com.carly.features.addcar.rp

import com.carly.core.data.datastore.CarDataStoreSource
import com.carly.core.data.local.CarsLocalDataSource
import com.carly.core.data.local.entities.UserCarEntity
import com.carly.core.dispatcher.DispatcherProvider
import com.carly.features.addcar.vm.SelectionItem
import com.carly.features.addcar.vm.UserCar
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AddCarRepositoryImpl(
    private val carsLocalDataSource: CarsLocalDataSource,
    private val carDataStoreSource: CarDataStoreSource,
    private val dispatcherProvider: DispatcherProvider
) : AddCarRepository {
    override fun getBrands(
    ): Flow<List<SelectionItem>> {
        return carsLocalDataSource.getBrands()
            .map { list -> list.map { item -> SelectionItem(item.brandId, item.brandName) } }
            .flowOn(dispatcherProvider.io)
    }


    override fun getSeries(brandId: Int): Flow<List<SelectionItem>> {
        return carsLocalDataSource.getSeries(brandId)
            .map { list -> list.map { item -> SelectionItem(item.seriesId, item.seriesName) } }
            .flowOn(dispatcherProvider.io)
    }

    override fun getBuildYears(
        seriesId: Int,
    ): Flow<List<SelectionItem>> {
        return flow {
            val series = carsLocalDataSource.getSeriesById(seriesId)
            val toList = (series.minimumSupportedYear..series.maximumSupportedYear).toList()
            emit(toList)
        }
            .map { list -> list.map { item -> SelectionItem(item, item.toString()) } }
            .flowOn(dispatcherProvider.io)
    }

    override fun getFuelTypes(): Flow<List<SelectionItem>> {
        return carsLocalDataSource.getFuelTypes()
            .map { list -> list.map { item -> SelectionItem(item.fuelTypeId, item.fuelTypeName) } }
            .flowOn(dispatcherProvider.io)
    }

    override suspend fun addUserCar(userCar: UserCar) = withContext(dispatcherProvider.io) {
        carsLocalDataSource.addUserCar(
            UserCarEntity(
                brandName = userCar.brand?.name ?: throw IllegalArgumentException("Brand is null"),
                seriesName = userCar.series?.name
                    ?: throw IllegalArgumentException("Series is null"),
                buildYear = userCar.year?.id ?: throw IllegalArgumentException("Year is null"),
                fuelType = userCar.fuelType?.name
                    ?: throw IllegalArgumentException("Fuel type is null"),
            )
        ).onSuccess {
            carDataStoreSource.setSelectedCarId(it)
        }
    }
}