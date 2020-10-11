package com.example.toebeanscatapp.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cats (
    val cat_url: String?,
    var cat_name: String?,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
)

