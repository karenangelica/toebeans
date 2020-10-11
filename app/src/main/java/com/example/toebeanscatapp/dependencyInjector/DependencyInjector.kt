package com.example.toebeanscatapp.dependencyInjector

import android.content.Context
import com.example.toebeanscatapp.api.CatRepository

interface DependencyInjector {
    fun catRepository(context: Context) : CatRepository
}