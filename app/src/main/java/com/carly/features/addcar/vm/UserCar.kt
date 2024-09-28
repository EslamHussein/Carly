package com.carly.features.addcar.vm


data class UserCar(
    val brand: SelectionItem? = null,
    val series: SelectionItem? = null,
    val year: SelectionItem? = null,
    val fuelType: SelectionItem? = null
) {
    fun toFormattedString(): String {
        return listOfNotNull(
            brand?.takeIf { it.name.isNotEmpty() },
            series?.takeIf { it.name.isNotEmpty() },
            year?.takeIf { it.name.isNotEmpty() },
            fuelType?.takeIf { it.name.isNotEmpty() }
        ).joinToString(separator = ", ")
    }
}
