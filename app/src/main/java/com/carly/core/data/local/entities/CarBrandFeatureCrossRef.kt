package com.carly.core.data.local.entities

import androidx.room.Entity

@Entity(primaryKeys = ["brandId", "featureId"])
data class CarBrandFeatureCrossRef(
    val brandId: Int,
    val featureId: Int
)