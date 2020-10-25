package com.example.toebeanscatapp.presenter



interface HomeContract {
    interface Presenter : BasePresenter<View> {
        fun onHappyBtnClicked()
        fun onDialogYesClicked(url: String, name: String)
        fun onFavoriteClicked()
        fun onYesBtnClicked()
    }

    interface View: BaseView {
        fun openDialog()
        fun navigateToFavorites()
        fun showCatPicture(url: String)
        fun setUrl(url : String)
        fun showLoadingDialog()
        fun hideLoadingDialog()
        fun showErrorToast(message : String)
    }
}