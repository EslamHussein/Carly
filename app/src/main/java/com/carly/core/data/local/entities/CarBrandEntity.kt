package com.carly.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_brand")
data class CarBrandEntity(
    @PrimaryKey val brandId: Int,
    val brandName: String
)