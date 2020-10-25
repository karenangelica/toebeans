package com.example.toebeanscatapp.presenter

import com.example.toebeanscatapp.api.CatRepository
import com.example.toebeanscatapp.roomdb.Cats
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception


class HomePresenter(private var catRepository: CatRepository) : HomeContract.Presenter {

    private var view: HomeContract.View? = null

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onHappyBtnClicked() {

        coroutineScope.launch {
            view?.showLoadingDialog()

            try {
                val value = catRepository.getRandomCatFromAPI()
                view?.showCatPicture(value)
                view?.setUrl(value)

                delay(2000)
                view?.hideLoadingDialog()
            } catch (e : Exception) {
                view?.showErrorToast(e.localizedMessage)
            }

        }

    }


//    override fun onHappyBtnClicked() {
//
//        catRepository.getRandomCat(object : CatCallback<String> {
//            override fun onSuccess(value: String) {
//                view?.showCatPicture(value)
//                view?.setUrl(value)
//            }
//
//            override fun onFailure(msg: String) {
//                //view?.Toast.makeText(this@HomeActivity, msg, Toast.LENGTH_SHORT).show()
//            }
//        })
//
//
//    }

    override fun onDialogYesClicked(url: String, name: String) {
        catRepository.insertCat(Cats(url, name))
        view?.navigateToFavorites()
    }

    override fun onFavoriteClicked() {
       view?.navigateToFavorites()
    }

    override fun onYesBtnClicked() {
        view?.openDialog()
    }

    override fun onViewReady(view: HomeContract.View) {
        onHappyBtnClicked()
        this.view = view
    }

    override fun onViewDetach() {
        view = null
    }

}

