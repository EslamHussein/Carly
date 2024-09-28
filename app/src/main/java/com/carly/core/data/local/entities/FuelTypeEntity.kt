package com.carly.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fuel_type")
class FuelTypeEntity(
    @PrimaryKey val fuelTypeId: Int,
    val fuelTypeName: String
)