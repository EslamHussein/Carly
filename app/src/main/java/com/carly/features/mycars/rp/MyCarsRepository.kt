package com.carly.features.mycars.rp

import com.carly.features.mycars.ui.dto.MyCar
import kotlinx.coroutines.flow.Flow

interface MyCarsRepository {

    fun getMyCars(): Flow<List<MyCar>>
    fun getSelectedCarId(): Flow<Long?>
    suspend fun deleteCar(myCar: MyCar): Result<Unit>
    suspend fun setSelectedCarId(carId: Long): Result<Unit>
}