package com.example.toebeanscatapp.api

interface CatCallback<T> {
    fun onSuccess(value: T)
    fun onFailure(msg: String)
}