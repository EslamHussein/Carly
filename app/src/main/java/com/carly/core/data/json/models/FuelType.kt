package com.carly.core.data.json.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FuelType(
    @SerialName("fuel_type_id")
    val fuelTypeId: Int,
    @SerialName("fuel_type_name")
    val fuelTypeName: String
)