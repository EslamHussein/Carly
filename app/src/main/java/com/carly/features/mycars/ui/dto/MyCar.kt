package com.carly.features.mycars.ui.dto

data class MyCar(
    val id: Long,
    val brandName: String,
    val seriesName: String,
    val buildYear: Int,
    val fuelType: String
)
