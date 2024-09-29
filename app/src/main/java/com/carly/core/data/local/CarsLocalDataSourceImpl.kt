package com.carly.core.data.local

import com.carly.core.asFailure
import com.carly.core.data.json.models.Brand
import com.carly.core.data.json.models.FuelType
import com.carly.core.data.local.dao.BrandDao
import com.carly.core.data.local.dao.CarBrandFeatureCrossRefDao
import com.carly.core.data.local.dao.FeatureDao
import com.carly.core.data.local.dao.FuelTypeDao
import com.carly.core.data.local.dao.SeriesDao
import com.carly.core.data.local.dao.UserCarDao
import com.carly.core.data.local.dto.SelectedCarWithFeaturesEntity
import com.carly.core.data.local.entities.CarBrandEntity
import com.carly.core.data.local.entities.CarBrandFeatureCrossRef
import com.carly.core.data.local.entities.FuelTypeEntity
import com.carly.core.data.local.entities.SeriesEntity
import com.carly.core.data.local.entities.SupportedFeatureEntity
import com.carly.core.data.local.entities.UserCarEntity
import kotlinx.coroutines.flow.Flow

class CarsLocalDataSourceImpl(
    private val carBrandDao: BrandDao,
    private val seriesDao: SeriesDao,
    private val featureDao: FeatureDao,
    private val fuelTypeDao: FuelTypeDao,
    private val carBrandFeatureCrossRefDao: CarBrandFeatureCrossRefDao,
    private val userCarDao: UserCarDao
) : CarsLocalDataSource {
    override suspend fun isDBEmpty(): Boolean {
        return carBrandDao.getCarBrandCount() == 0
    }

    override suspend fun insertCarBrands(carBrandList: List<Brand>) {
        carBrandList.forEach { carBrandWithSeries ->
            // Insert CarBrand
            val carBrandEntity =
                CarBrandEntity(
                    brandId = carBrandWithSeries.brandId,
                    brandName = carBrandWithSeries.brandName
                )
            carBrandDao.insertCarBrand(carBrandEntity)

            // Insert CarSeries
            carBrandWithSeries.brandSeries.forEach { series ->
                seriesDao.insertCarSeries(
                    SeriesEntity(
                        seriesId = series.seriesId,
                        brandId = carBrandEntity.brandId,
                        seriesName = series.seriesName,
                        maximumSupportedYear = series.maximumSupportedYear,
                        minimumSupportedYear = series.minimumSupportedYear
                    )
                )
            }

            // Insert SupportedFeatures
            carBrandWithSeries.supportedFeatures.forEach { feature ->
                featureDao.insertSupportedFeature(
                    SupportedFeatureEntity(
                        featureId = feature.featureId,
                        featureName = feature.featureName
                    )
                )
            }

            // Insert Cross-Reference
            carBrandWithSeries.supportedFeatures.forEach { feature ->
                val crossRef = CarBrandFeatureCrossRef(
                    brandName = carBrandEntity.brandName,
                    featureId = feature.featureId
                )
                carBrandFeatureCrossRefDao.insertCarBrandFeatureCrossRef(crossRef)
            }
        }

    }

    override fun getBrands(): Flow<List<CarBrandEntity>> {
        return carBrandDao.searchBrandsByName()
    }

    override fun getSeries(brandId: Int): Flow<List<SeriesEntity>> {
        return seriesDao.searchSeriesByName(brandId)
    }

    override fun getSeriesById(seriesId: Int): SeriesEntity {
        return seriesDao.getSeriesById(seriesId)
    }

    override fun getFuelTypes(): Flow<List<FuelTypeEntity>> {
        return fuelTypeDao.getFuelTypes()
    }

    override suspend fun addFuelTypes(fuelTypes: List<FuelType>) {
        fuelTypeDao.insertFuel(fuelTypes.map {
            FuelTypeEntity(
                fuelTypeId = it.fuelTypeId,
                fuelTypeName = it.fuelTypeName
            )
        })
    }

    override suspend fun addUserCar(car: UserCarEntity): Result<Long> {
        return runCatching {
            userCarDao.insertCar(car)
        }.onFailure {
            it.asFailure<Throwable>()
        }
    }

    override fun getAllUserCars(): Flow<List<UserCarEntity>> {
        return userCarDao.getAllCars()
    }

    override fun getUserCarByIdWithSupportedFeatures(carId: Long): Flow<SelectedCarWithFeaturesEntity?> {
        return userCarDao.getSelectedCarWithFeatures(carId)
    }

    override suspend fun deleteUserCarById(carId: Long): Result<Unit> {
        return runCatching { userCarDao.deleteCarById(carId) }.onFailure { it.asFailure<Throwable>() }
    }
}