package com.example.toebeanscatapp.api
import com.example.toebeanscatapp.api.models.RandomKitty
import com.example.toebeanscatapp.roomdb.Cats
import retrofit2.Response

interface CatRepository {

    suspend fun getRandomCatFromAPI() : String
    //fun getRandomCat(callback: CatCallback<String>)
    fun getCats() : List<Cats>
    fun insertCat(cat:Cats)

}