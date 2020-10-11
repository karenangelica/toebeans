package com.example.toebeanscatapp.presenter

import com.example.toebeanscatapp.api.CatRepository

class FavoritePresenter(private var catRepository: CatRepository) : FavoriteContract.Presenter {

    var view: FavoriteContract.View? = null

    override fun getCatList() {
        catRepository.getCats() // getting list of cats
        view?.showCatList(catRepository.getCats())
    }

    override fun onHomeClicked() {
        view?.navigateToHome()
    }

    override fun onViewReady(view: FavoriteContract.View) {
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }

}