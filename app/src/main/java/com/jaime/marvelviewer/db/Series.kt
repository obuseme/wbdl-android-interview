package com.jaime.marvelviewer.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Series(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "thumbnail") val thumbnail: String?,
    @ColumnInfo(name = "start_year") val startYear: Int?,
    @ColumnInfo(name = "end_year") val endYear: Int?,
    @ColumnInfo(name = "rating") val rating: String?,
    @ColumnInfo(name = "available") val available: Int?
)