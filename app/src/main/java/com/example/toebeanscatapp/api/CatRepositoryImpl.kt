package com.example.toebeanscatapp.api
import com.example.toebeanscatapp.api.models.RandomKitty
import com.example.toebeanscatapp.roomdb.CatDao
import com.example.toebeanscatapp.roomdb.Cats
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CatRepositoryImpl(private val catDao: CatDao) : CatRepository {

    override fun getRandomCat(callback: CatCallback<String>) {

        createCatAPIService().getRandomCatFromAPI().enqueue(object : Callback<List<RandomKitty>> {
            override fun onFailure(call: Call<List<RandomKitty>>, t: Throwable) {
                callback.onFailure("${t::class.simpleName}")
            }

            override fun onResponse(
                call: Call<List<RandomKitty>>,
                response: Response<List<RandomKitty>>
            ) {
                if (response.body().isNullOrEmpty()) {
                    callback.onFailure("response is empty")
                } else {
                    callback.onSuccess(response.body()!!.first().url)
                }
            }

        })

    }

    override fun getCats(): List<Cats> {
       return catDao.getAllCats()
    }

    override fun insertCat(cat: Cats) {
        catDao.insertALL(cat)
    }
}

