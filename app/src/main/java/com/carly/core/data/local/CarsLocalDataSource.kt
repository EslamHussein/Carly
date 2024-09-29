package com.carly.core.data.local

import com.carly.core.data.json.models.Brand
import com.carly.core.data.json.models.FuelType
import com.carly.core.data.local.dto.SelectedCarWithFeaturesEntity
import com.carly.core.data.local.entities.CarBrandEntity
import com.carly.core.data.local.entities.FuelTypeEntity
import com.carly.core.data.local.entities.SeriesEntity
import com.carly.core.data.local.entities.UserCarEntity
import kotlinx.coroutines.flow.Flow

/**
 * Local data source for cars
 */
interface CarsLocalDataSource {
    /**
     * Check if the database is empty
     */
    suspend fun isDBEmpty(): Boolean

    /**
     * Insert car brands into the database
     */
    suspend fun insertCarBrands(carBrandList: List<Brand>)

    /**
     * Insert car series into the database
     */
    fun getBrands(): Flow<List<CarBrandEntity>>

    /**
     * Get series for a specific brand
     */
    fun getSeries(brandId: Int): Flow<List<SeriesEntity>>

    /**
     * Get series by id
     */
    fun getSeriesById(seriesId: Int): SeriesEntity

    /**
     * Insert fuel types into the database
     */
    fun getFuelTypes(): Flow<List<FuelTypeEntity>>

    /**
     * Insert fuel types into the database
     */
    suspend fun addFuelTypes(fuelTypes: List<FuelType>)

    /**
     * Insert user car into the database
     */
    suspend fun addUserCar(car: UserCarEntity): Result<Long>

    /**
     * Get all user cars
     */
    fun getAllUserCars(): Flow<List<UserCarEntity>>

    /**
     * Get user car by id
     */
    fun getUserCarByIdWithSupportedFeatures(carId: Long): Flow<SelectedCarWithFeaturesEntity?>

    /**
     * Delete user car by id
     */
    suspend fun deleteUserCarById(carId: Long): Result<Unit>
}