package com.carly.core.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.carly.core.data.local.entities.SeriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SeriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCarSeries(seriesEntity: SeriesEntity)


    @Query(
        """
        SELECT * FROM car_series 
        WHERE brandId = :brandId
        """
    )
    fun searchSeriesByName(brandId: Int): Flow<List<SeriesEntity>>

    @Query("SELECT * FROM car_series WHERE seriesId = :seriesId")
    fun getSeriesById(seriesId: Int): SeriesEntity

}