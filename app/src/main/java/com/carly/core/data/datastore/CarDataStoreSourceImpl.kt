package com.carly.core.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CarDataStoreSourceImpl(private val dataStore: DataStore<Preferences>) : CarDataStoreSource {

    companion object {
        private val SELECTED_CAR_ID_KEY = longPreferencesKey("SELECTED_CAR_ID_KEY")
    }

    override fun getSelectedCarId(): Flow<Long?> {
        return dataStore.data.map { preferences -> preferences[SELECTED_CAR_ID_KEY] }
    }

    override suspend fun setSelectedCarId(carId: Long) {
        dataStore.edit { preferences ->
            preferences[SELECTED_CAR_ID_KEY] = carId
        }
    }
}