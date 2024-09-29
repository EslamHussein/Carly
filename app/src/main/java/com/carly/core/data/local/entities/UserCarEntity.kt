package com.carly.core.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_car")
class UserCarEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val brandName: String,
    val seriesName: String,
    val buildYear: Int,
    val fuelType: String,
)
