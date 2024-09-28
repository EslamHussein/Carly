package com.carly.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "supported_feature")
data class SupportedFeatureEntity(
    @PrimaryKey val featureId: Int,
    val featureName: String
)