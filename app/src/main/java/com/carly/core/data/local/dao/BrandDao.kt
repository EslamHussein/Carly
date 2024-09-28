package com.carly.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carly.core.data.local.entities.CarBrandEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BrandDao {
    @Query("SELECT COUNT(*) FROM car_brand")
    fun getCarBrandCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCarBrand(carBrandEntity: CarBrandEntity)

    @Query("SELECT * FROM car_brand")
    fun searchBrandsByName(): Flow<List<CarBrandEntity>>

}