package com.carly.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.carly.core.data.local.dto.SelectedCarWithFeaturesEntity
import com.carly.core.data.local.entities.UserCarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserCarDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCar(car: UserCarEntity): Long

    @Query("SELECT * FROM user_car")
    fun getAllCars(): Flow<List<UserCarEntity>>

    @Query("SELECT * FROM user_car WHERE id = :carId")
    suspend fun getUserCarById(carId: Long): UserCarEntity?


    @Transaction
    @Query(
        """
        SELECT * FROM user_car 
        WHERE id = :carId
    """
    )
    fun getSelectedCarWithFeatures(carId: Long): Flow<SelectedCarWithFeaturesEntity?>


}