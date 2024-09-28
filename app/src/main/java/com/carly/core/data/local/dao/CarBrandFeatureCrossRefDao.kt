package com.carly.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.carly.core.data.local.entities.CarBrandFeatureCrossRef


@Dao
interface CarBrandFeatureCrossRefDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCarBrandFeatureCrossRef(crossRef: CarBrandFeatureCrossRef)
}