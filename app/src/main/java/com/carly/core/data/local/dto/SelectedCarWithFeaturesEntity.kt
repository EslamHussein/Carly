package com.carly.core.data.local.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.carly.core.data.local.entities.CarBrandEntity
import com.carly.core.data.local.entities.CarBrandFeatureCrossRef
import com.carly.core.data.local.entities.SupportedFeatureEntity
import com.carly.core.data.local.entities.UserCarEntity


data class SelectedCarWithFeaturesEntity(
    @Embedded val car: UserCarEntity,
    @Relation(
        parentColumn = "brandName",
        entityColumn = "brandName"
    )
    val brand: CarBrandEntity,
    @Relation(
        entity = SupportedFeatureEntity::class,
        parentColumn = "brandName",
        entityColumn = "featureId",
        associateBy = Junction(CarBrandFeatureCrossRef::class)
    )
    val features: List<SupportedFeatureEntity>
)