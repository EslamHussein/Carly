package com.carly.features.addcar.vm


data class CreateUserCar(
    val brand: SelectionItem? = null,
    val series: SelectionItem? = null,
    val year: SelectionItem? = null,
    val fuelType: SelectionItem? = null
) {
    fun toFormattedString(): String {
        return listOfNotNull(
            brand?.name?.takeIf { it.isNotEmpty() },
            series?.name?.takeIf { it.isNotEmpty() },
            year?.name?.takeIf { it.isNotEmpty() },
            fuelType?.name?.takeIf { it.isNotEmpty() }
        ).joinToString(separator = ", ")
    }
}
