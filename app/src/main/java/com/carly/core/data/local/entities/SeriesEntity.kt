package com.carly.core.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "car_series",
    foreignKeys = [
        ForeignKey(
            entity = CarBrandEntity::class,
            parentColumns = ["brandId"],
            childColumns = ["brandId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["brandId"])]  // Add index here

)
data class SeriesEntity(
    @PrimaryKey val seriesId: Int,
    val seriesName: String,
    val minimumSupportedYear: Int,
    val maximumSupportedYear: Int,
    val brandId: Int // Foreign key
)