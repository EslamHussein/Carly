package com.carly.core.data.json.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CarsData (
    @SerialName("car_brands")
    val brands: List<Brand>,
    @SerialName("fuel_types")
    val fuelTypes: List<FuelType>
)