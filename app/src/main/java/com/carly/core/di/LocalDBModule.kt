package com.carly.core.di

import androidx.room.Room
import com.carly.core.data.local.CarsLocalDataSource
import com.carly.core.data.local.CarsLocalDataSourceImpl
import com.carly.core.data.local.dao.BrandDao
import com.carly.core.data.local.dao.CarBrandFeatureCrossRefDao
import com.carly.core.data.local.dao.FeatureDao
import com.carly.core.data.local.dao.FuelTypeDao
import com.carly.core.data.local.dao.SeriesDao
import com.carly.core.data.local.db.CarlyDatabase
import org.koin.dsl.module

val localDBModule = module {
    single<CarlyDatabase> {
        Room.databaseBuilder(
            get(),
            CarlyDatabase::class.java,
            "carly_database"
        ).build()
    }
    single<BrandDao> { get<CarlyDatabase>().carBrandDao() }
    single<SeriesDao> { get<CarlyDatabase>().carSeriesDao() }
    single<FeatureDao> { get<CarlyDatabase>().featureDao() }
    single<CarBrandFeatureCrossRefDao> { get<CarlyDatabase>().carBrandFeatureCrossRefDao() }
    single<FuelTypeDao> { get<CarlyDatabase>().fuelTypeDao() }

    single<CarsLocalDataSource> { CarsLocalDataSourceImpl(get(), get(), get(), get(), get()) }
}