package com.carly.core.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["brandName", "featureId"])
data class CarBrandFeatureCrossRef(
    val brandName: String,
    val featureId: Int
)