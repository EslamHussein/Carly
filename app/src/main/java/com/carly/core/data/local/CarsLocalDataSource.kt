package com.carly.core.data.local

import com.carly.core.data.json.models.Brand
import com.carly.core.data.json.models.FuelType
import com.carly.core.data.local.entities.CarBrandEntity
import com.carly.core.data.local.entities.FuelTypeEntity
import com.carly.core.data.local.entities.SeriesEntity
import kotlinx.coroutines.flow.Flow

interface CarsLocalDataSource {
    suspend fun isDBEmpty(): Boolean
    suspend fun insertCarBrands(carBrandList: List<Brand>)
    fun getBrands(): Flow<List<CarBrandEntity>>
    fun getSeries(brandId: Int): Flow<List<SeriesEntity>>
    fun getSeriesById(seriesId: Int): SeriesEntity
    fun getFuelTypes(): Flow<List<FuelTypeEntity>>
    suspend fun addFuelTypes(fuelTypes: List<FuelType>)
}