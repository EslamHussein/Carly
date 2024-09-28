package com.carly.core.di

import android.app.Application
import com.carly.core.data.json.CarsJsonDataSource
import com.carly.core.data.json.CarsJsonDataSourceImpl
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val jsonModule = module {

    single<Json> {
        Json {
            ignoreUnknownKeys = true
        }
    }
    single<CarsJsonDataSource> { CarsJsonDataSourceImpl(get<Application>().assets, get(), get()) }
}