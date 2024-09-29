package com.carly.core.mapper

import com.carly.core.data.local.dto.SelectedCarWithFeaturesEntity
import com.carly.core.data.local.entities.SupportedFeatureEntity
import com.carly.core.data.local.entities.UserCarEntity
import com.carly.features.dashboard.ui.dto.SelectedCarWithFeatures
import com.carly.features.dashboard.ui.dto.SupportedFeature
import com.carly.features.dashboard.ui.dto.UserCar
import com.carly.features.mycars.ui.dto.MyCar

fun UserCarEntity.toUserCar(): UserCar {
    return UserCar(
        id = id,
        brandName = brandName,
        seriesName = seriesName,
        buildYear = buildYear,
        fuelType = fuelType
    )
}

fun SupportedFeatureEntity.toSupportedFeature(): SupportedFeature {

    return SupportedFeature(
        id = featureId,
        name = featureName
    )
}

fun SelectedCarWithFeaturesEntity.toSelectedCarWithFeatures(): SelectedCarWithFeatures {
    return this.let { entity ->
        SelectedCarWithFeatures(
            car = entity.car.toUserCar(),
            features = entity.features.map { it.toSupportedFeature() }
        )
    }
}


fun UserCarEntity.toMyCar(): MyCar {
    return MyCar(
        id = id,
        brandName = brandName,
        seriesName = seriesName,
        buildYear = buildYear,
        fuelType = fuelType
    )
}