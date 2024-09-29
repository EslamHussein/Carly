package com.carly.core.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.carly.core.data.datastore.CarDataStoreSource
import com.carly.core.data.datastore.CarDataStoreSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val Context.dataStore by preferencesDataStore(name = "carly_data_store")

val dataStoreModule = module {
    single {
        androidContext().dataStore
    }

    single<CarDataStoreSource> { CarDataStoreSourceImpl(get()) }
}