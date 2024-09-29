package com.carly.features.dashboard.ui.dto


data class UserCar(
    val id: Long,
    val brandName: String,
    val seriesName: String,
    val buildYear: Int,
    val fuelType: String
)