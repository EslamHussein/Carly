package com.carly.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carly.core.data.local.entities.FuelTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FuelTypeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFuel(fuelTypes: List<FuelTypeEntity>)

    @Query("SELECT * FROM fuel_type")
    fun getFuelTypes(): Flow<List<FuelTypeEntity>>

}