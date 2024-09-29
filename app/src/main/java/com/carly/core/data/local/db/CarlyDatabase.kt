package com.carly.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carly.core.data.local.dao.BrandDao
import com.carly.core.data.local.dao.CarBrandFeatureCrossRefDao
import com.carly.core.data.local.dao.FeatureDao
import com.carly.core.data.local.dao.FuelTypeDao
import com.carly.core.data.local.dao.SeriesDao
import com.carly.core.data.local.dao.UserCarDao
import com.carly.core.data.local.entities.CarBrandEntity
import com.carly.core.data.local.entities.CarBrandFeatureCrossRef
import com.carly.core.data.local.entities.FuelTypeEntity
import com.carly.core.data.local.entities.SeriesEntity
import com.carly.core.data.local.entities.SupportedFeatureEntity
import com.carly.core.data.local.entities.UserCarEntity

/**
 * Database for Carly
 */
@Database(
    entities = [
        CarBrandEntity::class,
        SeriesEntity::class,
        SupportedFeatureEntity::class,
        CarBrandFeatureCrossRef::class,
        FuelTypeEntity::class,
        UserCarEntity::class
    ],
    version = 1
)
abstract class CarlyDatabase : RoomDatabase() {
    /**
     * DAO for car brands
     */
    abstract fun carBrandDao(): BrandDao

    /**
     * DAO for car series
     */
    abstract fun carSeriesDao(): SeriesDao

    /**
     * DAO for features
     */
    abstract fun featureDao(): FeatureDao

    /**
     * DAO for car brand features
     */
    abstract fun carBrandFeatureCrossRefDao(): CarBrandFeatureCrossRefDao

    /**
     * DAO for fuel types
     */
    abstract fun fuelTypeDao(): FuelTypeDao

    /**
     * DAO for user cars
     */
    abstract fun userCarDaoDao(): UserCarDao
}