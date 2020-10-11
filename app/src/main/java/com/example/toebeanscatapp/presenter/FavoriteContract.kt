package com.example.toebeanscatapp.presenter

import com.example.toebeanscatapp.roomdb.Cats

interface FavoriteContract {
    interface Presenter : BasePresenter<View> {
        fun getCatList()
        fun onHomeClicked()
    }

    interface View: BaseView {
        fun navigateToHome()
        fun showCatList(catList: List<Cats>)
    }
}