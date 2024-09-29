package com.carly.features.addcar.rp

import com.carly.features.addcar.vm.SelectionItem
import com.carly.features.addcar.vm.UserCar
import kotlinx.coroutines.flow.Flow

/**
 * Repository for adding a new car
 */
interface AddCarRepository {
    /**
     * Get car brands
     */
    fun getBrands(
    ): Flow<List<SelectionItem>>

    /**
     * Get car series for a specific brand
     */
    fun getSeries(
        brandId: Int
    ): Flow<List<SelectionItem>>

    /**
     * Get build years for a specific series
     */
    fun getBuildYears(
        seriesId: Int,
    ): Flow<List<SelectionItem>>

    /**
     * Get fuel types
     */
    fun getFuelTypes(): Flow<List<SelectionItem>>

    /**
     * Get transmission types
     */
    suspend fun addUserCar(userCar: UserCar):Result<Long>

}