package com.example.toebeanscatapp.api
import com.example.toebeanscatapp.roomdb.Cats

interface CatRepository {

    fun getRandomCat(callback: CatCallback<String>)
    fun getCats() : List<Cats>
    fun insertCat(cat:Cats)
}