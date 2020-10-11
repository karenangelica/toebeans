package com.example.toebeanscatapp.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CatDao {
    @Query("SELECT * FROM Cats")
            fun getAllCats(): List<Cats>
    @Insert
    fun insertALL(cat: Cats)

    @Delete
    fun delete(cat: Cats)
}