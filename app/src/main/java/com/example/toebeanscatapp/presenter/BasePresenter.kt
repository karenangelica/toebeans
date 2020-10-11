package com.example.toebeanscatapp.presenter

interface BasePresenter<T: BaseView> {
    fun onViewReady(view: T)
    fun onViewDetach()
}