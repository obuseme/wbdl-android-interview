package com.jaime.marvelviewer.db

import androidx.room.*

@Dao
interface SeriesDAO {
    /**
     * TODO: Requirements specify 'The results should be sorted by start year.' My interpretation is this means 'chronologically'
     * Change query to 'DESC' if this is incorrect
     */
    @Query("SELECT * FROM Series ORDER BY start_year ASC")
    fun getAll(): List<Series>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(series: List<Series>)

    @Query("DELETE FROM Series")
    fun deleteAll()
}