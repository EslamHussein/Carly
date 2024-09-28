package com.carly.core.data.json.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Brand(
    @SerialName("brand_id")
    val brandId: Int,
    @SerialName("brand_name")
    val brandName: String,
    @SerialName("brand_series")
    val brandSeries: List<CarSeries>,
    @SerialName("supported_features")
    val supportedFeatures: List<SupportedFeature> = emptyList()
)
