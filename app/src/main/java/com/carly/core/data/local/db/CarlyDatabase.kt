package com.carly.core.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.carly.core.data.local.dao.BrandDao
import com.carly.core.data.local.dao.CarBrandFeatureCrossRefDao
import com.carly.core.data.local.dao.FeatureDao
import com.carly.core.data.local.dao.FuelTypeDao
import com.carly.core.data.local.dao.SeriesDao
import com.carly.core.data.local.entities.CarBrandEntity
import com.carly.core.data.local.entities.CarBrandFeatureCrossRef
import com.carly.core.data.local.entities.FuelTypeEntity
import com.carly.core.data.local.entities.SeriesEntity
import com.carly.core.data.local.entities.SupportedFeatureEntity

@Database(
    entities = [
        CarBrandEntity::class,
        SeriesEntity::class,
        SupportedFeatureEntity::class,
        CarBrandFeatureCrossRef::class,
        FuelTypeEntity::class
    ],
    version = 1
)
abstract class CarlyDatabase : RoomDatabase() {
    abstract fun carBrandDao(): BrandDao
    abstract fun carSeriesDao(): SeriesDao
    abstract fun featureDao(): FeatureDao
    abstract fun carBrandFeatureCrossRefDao(): CarBrandFeatureCrossRefDao
    abstract fun fuelTypeDao(): FuelTypeDao
}