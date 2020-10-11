package com.example.toebeanscatapp.dependencyInjector

import android.content.Context
import com.example.toebeanscatapp.api.CatRepository
import com.example.toebeanscatapp.api.CatRepositoryImpl
import com.example.toebeanscatapp.roomdb.CatRoomDatabase

object DependencyInjectorImpl : DependencyInjector {
    override fun catRepository(context : Context): CatRepository {
        return CatRepositoryImpl (CatRoomDatabase.getDatabase(context).catDao())
    }
}