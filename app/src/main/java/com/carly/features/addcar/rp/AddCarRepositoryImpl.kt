package com.carly.features.addcar.rp

import com.carly.core.data.local.CarsLocalDataSource
import com.carly.core.dispatcher.DispatcherProvider
import com.carly.features.addcar.vm.SelectionItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class AddCarRepositoryImpl(
    private val carsLocalDataSource: CarsLocalDataSource,
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
}