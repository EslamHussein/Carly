package com.carly.core.data.datastore

import kotlinx.coroutines.flow.Flow

interface CarDataStoreSource {

    fun getSelectedCarId(): Flow<Long?>
    suspend fun setSelectedCarId(carId: Long)
}