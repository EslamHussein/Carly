package com.carly.core.data.json.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupportedFeature(
    @SerialName("feature_id")
    val featureId: Int,
    @SerialName("feature_name")
    val featureName: String
)