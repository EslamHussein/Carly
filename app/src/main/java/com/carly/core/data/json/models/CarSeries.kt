package com.carly.core.data.json.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CarSeries(
    @SerialName("series_id")
    val seriesId: Int,
    @SerialName("series_name")
    val seriesName: String,
    @SerialName("minimum_supported_year")
    val minimumSupportedYear: Int,
    @SerialName("maximum_supported_year")
    val maximumSupportedYear: Int
)