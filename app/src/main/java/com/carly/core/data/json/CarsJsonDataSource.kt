package com.carly.core.data.json

import com.carly.core.data.json.models.CarsData

interface CarsJsonDataSource {

    suspend fun getBrands(): CarsData
}