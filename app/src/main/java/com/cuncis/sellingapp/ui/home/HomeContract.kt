package com.cuncis.sellingapp.ui.home

interface HomeContract {

    interface View {
        fun initListener()
        fun showMessage(message: String)
    }

}