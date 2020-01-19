package com.cuncis.sellingapp.ui.home

class HomePresenter(view: HomeContract.View) {

    init {
        view.initListener()
    }

}