package com.carly.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.carly.core.data.local.entities.SupportedFeatureEntity

@Dao
interface FeatureDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSupportedFeature(supportedFeatureEntity: SupportedFeatureEntity)
} 