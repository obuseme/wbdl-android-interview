package com.jaime.marvelviewer.db

import androidx.room.*

@Dao
interface ComicDAO {
    /**
     * TODO: Requirements specify 'The results should be sorted by start year.' My interpretation is this means 'chronologically'
     * Change query to 'DESC' if this is incorrect
     */
    @Query("SELECT * FROM Comic ORDER BY start_year ASC")
    fun getAll(): List<Comic>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(comics: List<Comic>)

    @Query("DELETE FROM Comic")
    fun deleteAll()
}