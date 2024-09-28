package com.carly.features.addcar.rp

import com.carly.features.addcar.vm.SelectionItem
import kotlinx.coroutines.flow.Flow

interface AddCarRepository {
    fun getBrands(
    ): Flow<List<SelectionItem>>

    fun getSeries(
        brandId: Int
    ): Flow<List<SelectionItem>>

    fun getBuildYears(
        seriesId: Int,
    ): Flow<List<SelectionItem>>

    fun getFuelTypes(): Flow<List<SelectionItem>>

}