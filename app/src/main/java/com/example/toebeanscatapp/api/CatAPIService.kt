package com.example.toebeanscatapp.api

import com.example.toebeanscatapp.api.models.Breeds
import com.example.toebeanscatapp.api.models.RandomKitty
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

fun createCatAPIService(): CatAPIService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.thecatapi.com/v1/")
        .addConverterFactory(MoshiConverterFactory.create())                                        //Serialize JSON file
        .build()

    return retrofit.create(CatAPIService::class.java)
    //returns all
    //https://api.thecatapi.com/v1/images/search?breed_ids={breed-id}
}

interface CatAPIService {

    //Get Random Cat Image @ annotation
    @GET("images/search") // end point when getting random pictures
    fun getRandomCatFromAPI(): Call<List<RandomKitty>>

    /*@GET("images/search")
    fun getRandomCatBreedFromAPI(
        @Query("breed_id") breed_id: String,
        @Query("api_key") api_key: String

    ) : Call<List<Breeds>>*/
}

